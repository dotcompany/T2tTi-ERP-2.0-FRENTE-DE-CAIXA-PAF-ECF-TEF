package nlink.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.Buffer;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import nlink.Holder;
import nlink.IllegalAnnotationError;
import nlink.MarshalAs;
import nlink.NativeType;

/**
 * Defines "toolkit" to introspect a COM-bound method.
 * 
 * @author Kohsuke Kawaguchi
 */
public class MethodIntrospector {

	final Method method;
	final Annotation[][] pa;
	final Type[] paramTypes;

	public MethodIntrospector(final Method method) {
		this.method = method;
		this.pa = method.getParameterAnnotations();
		this.paramTypes = method.getParameterTypes();
	}

	public final MarshalAs getMarshalAs(final int idx) {
		for (Annotation a : pa[idx])
			if (a instanceof MarshalAs) {
				return (MarshalAs) a;
			}
		return null;
	}

	public final NativeType getParamConversion(final int idx) {
		MarshalAs ma = getMarshalAs(idx);
		if (ma != null) {
			return ma.value();
		} else {
			return getDefaultConversion(paramTypes[idx]);
		}
	}

	public final int paramLength() {
		return pa.length;
	}

	public final NativeType getReturnConversion() {
		MarshalAs rt = method.getAnnotation(MarshalAs.class);
		if (rt != null) {
			return rt.value();
		} else {
			// guess the default
			if (method.getReturnType() == Void.TYPE) {
				// no return type
				return NativeType.Default; // unused
			} else {
				return getDefaultConversion(method.getReturnType());
			}
		}
	}

	private static final Map<Class<?>, NativeType> defaultConversions = new HashMap<Class<?>, NativeType>();

	static {
		defaultConversions.put(double.class, NativeType.Double);
		defaultConversions.put(float.class, NativeType.Float);
		defaultConversions.put(int.class, NativeType.Int32);
		defaultConversions.put(short.class, NativeType.Int16);
		defaultConversions.put(byte.class, NativeType.Int8);
		defaultConversions.put(boolean.class, NativeType.VariantBool);
		defaultConversions.put(String.class, NativeType.Unicode);
		defaultConversions.put(Date.class, NativeType.Date);
	}

	/**
	 * Computes the default conversion for the given type.
	 */
	static NativeType getDefaultConversion(final Type t) {
		if (t instanceof Class) {
			Class<?> c = (Class<?>) t;
			NativeType r = defaultConversions.get(c);
			if (r != null)
				return r;

			if (Enum.class.isAssignableFrom(c))
				return NativeType.Int32;
			if (Buffer.class.isAssignableFrom(c))
				return NativeType.PVOID;
			if (Calendar.class.isAssignableFrom(c))
				return NativeType.Date;
		}

		if (t instanceof ParameterizedType) {
			ParameterizedType p = (ParameterizedType) t;
			if (p.getRawType() == Holder.class) {
				// let p=Holder<V>
				Type v = p.getActualTypeArguments()[0];
				if (String.class == v)
					return NativeType.BSTR_ByRef;
				if (Integer.class == v
						|| Enum.class.isAssignableFrom((Class<?>) v))
					return NativeType.Int32_ByRef;
				if (Boolean.class == v)
					return NativeType.VariantBool_ByRef;
			}
		}

		throw new IllegalAnnotationError("no default conversion available for "
				+ t);
	}
}
