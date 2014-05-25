package nlink;

import static nlink.Const.BYREF;

import java.lang.reflect.Type;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Native method type.
 * 
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public enum NativeType {
	/**
	 * <tt>BSTR</tt>.
	 * 
	 * <p>
	 * Expected Java type: String
	 */
	BSTR(1, 4),

	/**
	 * <tt>BSTR*</tt>.
	 * 
	 * TODO: support StringBuffer
	 * <p>
	 * Expected Java type: {@link Holder}<String>
	 */
	BSTR_ByRef(1 | BYREF, 4),

	/**
	 * <tt>LPWSTR</tt>.
	 * 
	 * More concretely, it becomes a L'\0'-terminated UTF-16LE format.
	 */
	Unicode(2, 4),
	/**
	 * String will be marshalled as "char*".
	 * 
	 * <p>
	 * More concretely, it becomes a '\0'-terminated multi-byte string where
	 * characters are encoded according to the platform default encoding.
	 * 
	 * <p>
	 * For example, on a typical English Windows system this is just an ASCII
	 * string (more or less). On a typical Japanese Windows system, this is
	 * Shift-JIS.
	 */
	CSTR(3, 4),

	/**
	 * <tt>INT8</tt> (byte).
	 * 
	 * <p>
	 * Expected Java type: byte {@link Number}
	 */
	Int8(100, 1), Int8_ByRef(100 | BYREF, 1),
	/**
	 * <tt>INT16</tt> (short).
	 * 
	 * <p>
	 * Expected Java type: short {@link Number}
	 */
	Int16(101, 2), Int16_ByRef(101 | BYREF, 2),

	/**
	 * Marshalled as 32-bit integer.
	 * 
	 * <p>
	 * Java "int" is 32 bit.
	 * 
	 * <p>
	 * Expected Java type: int {@link Number} {@link Enum}
	 */
	Int32(102, 4) {
		// the native code will see the raw pointer value as Integer
		@Override
		public Object massage(final Object param) {
			if (param == null)
				return null;

			Class<?> clazz = param.getClass();

			if (Enum.class.isAssignableFrom(clazz)) {
				// if it's an enum constant, change it to the number
				return EnumDictionary.get((Class<? extends Enum>) clazz).value(
						(Enum) param);
			}
			return param;
		}

		@Override
		public Object unmassage(final Class<?> type,
				final Type genericSignature, final Object param) {
			if (Enum.class.isAssignableFrom(type)) {
				return EnumDictionary.get((Class<? extends Enum>) type)
						.constant((Integer) param);
			}

			return param;
		}
	},
	Int32_ByRef(102 | BYREF, 4),

	/**
	 * The native type is 'BOOL' (defined as 'int') where <tt>true</tt> maps
	 * to -1 and <tt>false</tt> maps to 0.
	 * 
	 * <p>
	 * Expected Java type: boolean {@link Boolean}
	 */
	Bool(103, 4),

	/**
	 * The native type is 'VARIANT_BOOL' where TRUE=1 and FALSE=0. Note that
	 * <tt>sizeof(VARIANT_BOOL)==2</tt>.
	 * 
	 * <p>
	 * Expected Java type: boolean {@link Boolean}
	 */
	VariantBool(104, 2), VariantBool_ByRef(104 | BYREF, 2),

	/**
	 * <tt>float</tt>.
	 * 
	 * <p>
	 * Expected Java type: boolean {@link Number}
	 */
	Float(120, 4),

	/**
	 * <tt>double</tt>.
	 * 
	 * <p>
	 * Expected Java type: boolean {@link Number}
	 */
	Double(121, 8),

	/**
	 * The native type is determined from the Java method return type. See the
	 * documentation for mor details. TODO: link to the doc.
	 */
	Default(201, 9999),

	/**
	 * <tt>PVOID</tt>.
	 * 
	 * <p>
	 * The assumed semantics is that a region of buffer will be passed to the
	 * native method.
	 * 
	 * <p>
	 * Expected Java type: direct {@link Buffer}s ({@link Buffer}s created
	 * from methods like {@link ByteBuffer#allocateDirect(int)}
	 */
	PVOID(304, 4),

	/**
	 * <tt>void**</tt>.
	 * 
	 * <p>
	 * The assumed semantics is that you receive a buffer.
	 * 
	 * <p>
	 * Expected Java type: {@link Holder}&lt;{@link Buffer}> ({@link Buffer}s
	 * created from methods like {@link ByteBuffer#allocateDirect(int)}
	 */
	PVOID_ByRef(304 | BYREF, 4),

	/**
	 * <tt>DATE</tt>.
	 * 
	 * See
	 * http://msdn.microsoft.com/library/default.asp?url=/library/en-us/vccore/html/_core_The_DATE_Type.asp
	 * <p>
	 * Expected Java type: {@link java.util.Date} {@link Calendar}
	 */
	Date(400, 8) {
		// the native code will see the raw pointer value as Integer
		@Override
		public Object massage(final Object param) {
			java.util.Date dt;
			if (param instanceof Calendar) {
				dt = ((Calendar) param).getTime();
			} else {
				dt = (java.util.Date) param;
			}

			// the number of milliseconds since January 1, 1970, 00:00:00 GMT
			long t = dt.getTime();
			// the number of milliseconds since January 1, 1970, 00:00:00 Local
			// Time
			t -= dt.getTimezoneOffset() * 60 * 1000;

			// the number of milliseconds since December 30, 1899, 00:00:00
			// Local Time
			t += 2209161600000L;

			// DATE is an offset from "30 December 1899"
			if (t < 0) {
				// -0.3 -> -0.7
				long offset = -(t % MSPD); // TODO: check
				t = t - MSPD + offset;
			}
			double d = ((double) t) / MSPD;
			return d;
		}

		@Override
		public Object unmassage(final Class<?> signature,
				final Type genericSignature, final Object param) {
			double d = (Double) param;
			long t = (long) (d * MSPD);
			t -= 2209161600000L;
			t -= defaultTimeZone.getOffset(t); // convert back to UTC
			java.util.Date dt = new java.util.Date(t);
			if (Calendar.class.isAssignableFrom(signature)) {
				GregorianCalendar cal = new GregorianCalendar();
				cal.setTime(dt);
				return cal;
			} else {
				return dt;
			}
		}
	},

	;

	/**
	 * Unique identifier of this constant. Passed to the native code.
	 */
	public final int code;

	/**
	 * Size of the native type in bytes.
	 */
	final int size;

	private static final Map<Integer, NativeType> codeMap = new HashMap<Integer, NativeType>();

	static {
		for (NativeType nt : values()) {
			codeMap.put(nt.code, nt);
		}
	}

	NativeType(final int code, final int size) {
		this.code = code;
		this.size = size;
	}

	/**
	 * Changes the parameter type before the parameter is passed to the native
	 * code.
	 * <p>
	 * This allows {@link NativeType}s to take more Java-friendly argument and
	 * convert it to more native code friendly form behind the scene.
	 * 
	 * @param param
	 *            can be null.
	 */
	public Object massage(final Object param) {
		return param;
	}

	/**
	 * Changes the parameter type before the method call returns.
	 * <p>
	 * The opposite of {@link #massage(java.lang.Object)}. Only useful for
	 * BYREFs.
	 * 
	 * @param signature
	 *            the parameter type in its raw form.
	 * @param genericSignature
	 *            the parameter type in its generified form.
	 * @param param
	 */
	public Object unmassage(final Class<?> signature,
			final Type genericSignature, final Object param) {
		return param;
	}

	/**
	 * If the constant has the BYREF version, return it. Otherwise null.
	 */
	public final NativeType byRef() {
		if (code == (code | BYREF)) {
			return null;
		}
		return codeMap.get(code | BYREF);
	}

	public final NativeType getNoByRef() {
		if (code == (code & (~BYREF))) {
			return null;
		}
		return codeMap.get(code & (~BYREF));
	}

	private static final long MSPD = 24 * 60 * 60 * 1000;
	private static final TimeZone defaultTimeZone = TimeZone.getDefault();
}
