package nlink.win32;

import nlink.NLinkException;

/**
 * Signals an error during the NLink processing.
 * 
 * @author Kohsuke Kawaguchi
 */
public class Win32NLinkException extends NLinkException {

	private static final long serialVersionUID = 1529746978177244594L;

	/**
	 * The value from {@code GetLastError}.
	 */
	private final int errorCode = Native.getLastError();

	/**
	 * The formatter message of {@link #errorCode}
	 */
	private final String errorCodeMessage = Native
			.formatErrorMessage(errorCode);

	public Win32NLinkException(final String msg, final String fileName,
			final int line) {
		super(msg, fileName, line);

	}

	public Win32NLinkException(final String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		if (errorCodeMessage == null) {
			return super.getMessage();
		}
		return super.getMessage() + " : " + errorCodeMessage;
	}
}
