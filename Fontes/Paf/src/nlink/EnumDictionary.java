package nlink;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Provides faster number &lt;-> enum conversion.
 * 
 * @author Kohsuke Kawaguchi (kk@kohsuke.org)
 */
abstract class EnumDictionary<T extends Enum<T>> {
	protected final Class<T> clazz;

	private EnumDictionary(final Class<T> clazz) {
		this.clazz = clazz;
		assert clazz.isEnum();
	}

	/**
	 * Looks up a dictionary from an enum class.
	 */
	public static <T extends Enum<T>> EnumDictionary<T> get(final Class<T> clazz) {
		EnumDictionary<T> dic = registry.get(clazz);
		if (dic == null) {
			boolean sparse = NLinkEnum.class.isAssignableFrom(clazz);
			if (sparse)
				dic = new Sparse<T>(clazz);
			else
				dic = new Continuous<T>(clazz);
			registry.put(clazz, dic);
		}
		return dic;
	}

	/**
	 * Gets the integer value for the given enum constant.
	 */
	abstract int value(Enum t);

	/**
	 * Gets the enum constant object from its integer value.
	 */
	abstract T constant(int v);

	/**
	 * For enum constants that doesn't use any {@link NLinkEnum}.
	 */
	static class Continuous<T extends Enum<T>> extends EnumDictionary<T> {
		private T[] consts;

		private Continuous(final Class<T> clazz) {
			super(clazz);
			consts = clazz.getEnumConstants();
		}

		@Override
		public int value(final Enum t) {
			return t.ordinal();
		}

		@Override
		public T constant(final int v) {
			return consts[v];
		}
	}

	/**
	 * For enum constants with {@link NLinkEnum}.
	 */
	static class Sparse<T extends Enum<T>> extends EnumDictionary<T> {
		private final Map<Integer, T> fromValue = new HashMap<Integer, T>();

		private Sparse(final Class<T> clazz) {
			super(clazz);

			T[] consts = clazz.getEnumConstants();
			for (T v : consts) {
				fromValue.put(((NLinkEnum) v).nativeEnumValue(), v);
			}
		}

		@Override
		public int value(final Enum t) {
			return ((NLinkEnum) t).nativeEnumValue();
		}

		@Override
		public T constant(final int v) {
			T t = fromValue.get(v);
			if (t == null)
				throw new IllegalArgumentException(clazz.getName()
						+ " has no constant of the value " + v);
			return t;
		}
	}

	private static final Map<Class<? extends Enum>, EnumDictionary> registry = Collections
			.synchronizedMap(new WeakHashMap<Class<? extends Enum>, EnumDictionary>());
}
