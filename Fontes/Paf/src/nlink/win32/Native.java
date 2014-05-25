package nlink.win32;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Kohsuke Kawaguchi
 */
class Native {
	static native int loadLibrary(String name);

	static native int getLastError();

	static native String formatErrorMessage(int errorCode);

	/**
	 * Invokes a method.
	 */
	static native Object invoke(int functionPtr, Object[] args,
			int[] parameterConversions, Class<?> returnType,
			int returnConversion);

	static native int getProcAddress2(int hModule, int integer);

	static native int getProcAddress(int hModule, String name);

	static {
		loadNativeLibrary();
	}

	static void loadNativeLibrary() {
		try {
			// load the native part of the code.
			// first try java.library.path
			System.loadLibrary("nlink");
			return;
		} catch (Throwable t) {
			;
		}

		// try loading com4j.dll in the same directory as com4j.jar
		URL res = Native.class.getClassLoader()
				.getResource("nlink/Const.class");
		String url = res.toExternalForm();
		if (url.startsWith("jar://")) {
			int idx = url.lastIndexOf('!');
			String filePortion = url.substring(6, idx);
			if (filePortion.startsWith("file://")) {
				File jarFile = new File(filePortion.substring(7));
				File dllFile = new File(jarFile.getParentFile(), "nlink.dll");
				System.load(dllFile.getPath());
				return;
			}
		}

		if (extract("nlink.dll")) {
			return;
		}

		throw new UnsatisfiedLinkError("Unable to load nlink.dll");
	}

	static boolean extract(final String fileName) {
		FileOutputStream os = null;
		InputStream is = null;
		String tempdir = System.getProperty("java.io.tmpdir");
		String path = tempdir + File.separator + fileName;
		File file = new File(path);

		try {
			if (!file.exists()) {
				is = Native.class.getResourceAsStream("/" + fileName);
				if (is != null) {
					int read;
					byte[] buffer = new byte[4096];
					os = new FileOutputStream(file);
					while ((read = is.read(buffer)) != -1) {
						os.write(buffer, 0, read);
					}
					os.close();
					is.close();
				}
			}
			load(file.getAbsolutePath());
			return true;
		} catch (Throwable ex) {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException ex1) {
			}
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException ex1) {
			}
		}
		if (file.exists()) {
			file.delete();
		}
		return false;
	}

	static boolean load(final String libName) {
		try {
			System.load(libName);
			return true;
		} catch (UnsatisfiedLinkError e) {
		}
		return false;
	}
}
