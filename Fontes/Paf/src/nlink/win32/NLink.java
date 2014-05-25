package nlink.win32;

import java.io.File;
import java.lang.reflect.Proxy;

import nlink.NLinkException;

/**
 * Entry point to the Win32 version of the NLink API.
 * 
 * @author Kohsuke Kawaguchi
 */
public abstract class NLink {
	private NLink() {
	} // no instantiation please

	/**
	 * Creates a method invocation converter for the given interface.
	 * 
	 * @param dllInterface
	 *            An interface with {@link DllClass} annotation. Must not be
	 *            null.
	 * 
	 * @return The created converter. Always non-null.
	 */
	public static <T> T create(final Class<T> dllInterface) {
		return create(dllInterface, null);
	}

	/**
	 * Creates a method invocation converter for the given interface.
	 * 
	 * <p>
	 * This is the extended version of {@link #create(Class)} that allows a DLL
	 * to be specified explicitly.
	 * 
	 * @param dllInterface
	 *            An interface with {@link DllClass} annotation. Must not be
	 *            null.
	 * 
	 * @param library
	 *            The full path of the DLL file to be loaded. Must not be null.
	 * 
	 * @return The created converter. Always non-null.
	 */
	public static <T> T create(final Class<T> dllInterface, final File library) {
		if (!dllInterface.isInterface())
			throw new NLinkException(dllInterface + " is not an interface");

		DllClass dc = dllInterface.getAnnotation(DllClass.class);
		if (dc == null)
			throw new NLinkException(dllInterface
					+ " does not have @DllClass annotation");

		String libPath;
		if (library != null) {
			libPath = library.toString();
		} else {
			libPath = dc.value();
			if (libPath.equals("")) {
				// default to the class name
				libPath = dllInterface.getSimpleName();
			}
		}

		return dllInterface.cast(Proxy.newProxyInstance(dllInterface
				.getClassLoader(), new Class[] { dllInterface }, new Wrapper(
				dllInterface, libPath)));
	}
}
