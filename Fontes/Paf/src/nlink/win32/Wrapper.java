package nlink.win32;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
import java.util.Map;

import nlink.Holder;
import nlink.NLinkException;
import nlink.NativeType;
import nlink.impl.MethodIntrospector;

/**
 * @author Kohsuke Kawaguchi
 */
class Wrapper implements InvocationHandler {
	private final int hModule;
	private final Map<Method, Invoker> methods = new HashMap<Method, Invoker>();

	Wrapper(final Class<?> dllInterface, final String libPath) {
		hModule = Native.loadLibrary(libPath);
		if (hModule == 0) {
			throw new Win32NLinkException("Failed to load library " + libPath);
		}

		// find DLL methods
		for (Method m : dllInterface.getDeclaredMethods()) {
			DllMethod dm = m.getAnnotation(DllMethod.class);
			if (dm == null) {
				continue; // not interested
			}

			methods.put(m, new Invoker(m, dm));
		}

		if (methods.isEmpty())
			throw new NLinkException("No @DllMethod found on " + dllInterface);
	}

	public Object invoke(final Object proxy, final Method method, Object[] args)
			throws Throwable {
		if (args == null) {
			args = EMPTY_ARRAY;
		}

		if (method.getDeclaringClass() == Object.class) {
			try {
				return method.invoke(this, args);
			} catch (InvocationTargetException e) {
				throw e.getTargetException();
			}
		}

		Invoker invoker = methods.get(method);
		if (invoker == null) {
			throw new NLinkException("Not a DLL method: " + method);
		}

		return invoker.invoke(args);
	}

	private class Invoker {
		final Method method;
		final int functionPtr;

		// list of params.code
		final int[] paramConvs;
		final NativeType[] params;
		final NativeType returnConv;
		final Class<?>[] paramTypes;
		final Type[] genericParamTypes;

		final boolean checkError;

		public Invoker(final Method m, final DllMethod dm) {
			String name = dm.value();
			if (name.equals("")) {
				int p = Native.getProcAddress(hModule, m.getName());
				if (p == 0) {
					// try 'W' version
					p = Native.getProcAddress(hModule, m.getName() + 'W');
				}
				functionPtr = p;
			} else if (name.startsWith("#")) {
				functionPtr = Native.getProcAddress2(hModule, Integer
						.parseInt(name.substring(1)));
			} else {
				functionPtr = Native.getProcAddress(hModule, name);
			}

			if (functionPtr == 0)
				throw new Win32NLinkException("Failed to find a function for "
						+ m.getName());

			method = m;

			checkError = m.getAnnotation(CheckLastError.class) != null;

			MethodIntrospector mi = new MethodIntrospector(m);

			Annotation[][] pa = m.getParameterAnnotations();
			int paramLen = pa.length;

			returnConv = mi.getReturnConversion();
			paramTypes = m.getParameterTypes();
			genericParamTypes = m.getGenericParameterTypes();
			paramConvs = new int[paramLen];
			params = new NativeType[paramLen];
			for (int i = 0; i < paramLen; i++) {
				NativeType n = mi.getParamConversion(i);
				params[i] = n;
				paramConvs[i] = n.code;
			}
		}

		Object invoke(final Object[] args) {
			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof Holder && params[i].getNoByRef() != null) {
					// massage the value of Holder, not the Holder itself
					Holder h = (Holder) args[i];
					h.value = params[i].getNoByRef().massage(h.value);
				} else {
					args[i] = params[i].massage(args[i]);
				}
			}

			try {
				Object r = Native.invoke(functionPtr, args, paramConvs, method
						.getReturnType(), returnConv.code);

				if (checkError && Native.getLastError() != 0) {
					throw new Win32NLinkException(method + " failed");
				}

				return returnConv.unmassage(method.getReturnType(), method
						.getGenericReturnType(), r);
			} finally {
				for (int i = 0; i < args.length; i++) {
					if (args[i] instanceof Holder
							&& params[i].getNoByRef() != null) {
						Holder h = (Holder) args[i];
						Type holderParamType = getTypeParameter(
								genericParamTypes[i], 0);
						h.value = params[i].getNoByRef().unmassage(
								erasure(holderParamType), holderParamType,
								h.value);
					} else {
						args[i] = params[i].unmassage(paramTypes[i],
								genericParamTypes[i], args[i]);
					}
				}
			}
		}

		private Type getTypeParameter(final Type t, final int index) {
			if (t instanceof ParameterizedType) {
				ParameterizedType pt = (ParameterizedType) t;
				return pt.getActualTypeArguments()[index];
			} else {
				return Object.class;
			}
		}

		private Class<?> erasure(final Type t) {
			if (t instanceof Class) {
				return (Class<?>) t;
			}
			if (t instanceof ParameterizedType) {
				ParameterizedType pt = (ParameterizedType) t;
				return erasure(pt.getRawType());
			}
			if (t instanceof WildcardType) {
				WildcardType wt = (WildcardType) t;
				Type[] ub = wt.getUpperBounds();
				if (ub.length == 0) {
					return Object.class;
				} else {
					return erasure(ub[0]);
				}
			}
			if (t instanceof GenericArrayType) {
				GenericArrayType ga = (GenericArrayType) t;
				return Array.newInstance(erasure(ga.getGenericComponentType()),
						0).getClass(); // ARGH!
			}
			if (t instanceof TypeVariable) {
				TypeVariable<?> tv = (TypeVariable<?>) t;
				Type[] ub = tv.getBounds();
				if (ub.length == 0) {
					return Object.class;
				} else {
					return erasure(ub[0]);
				}
			}
			throw new IllegalArgumentException(t.toString());
		}
	}

	private static final Object[] EMPTY_ARRAY = new Object[0];
}
