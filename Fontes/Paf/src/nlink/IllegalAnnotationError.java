package nlink;

/**
 * Signals an error in the use of NLink annotations.
 * 
 * @author Kohsuke Kawaguchi
 */
public class IllegalAnnotationError extends Error {

	private static final long serialVersionUID = -1124746468968915320L;

	public IllegalAnnotationError(final String message) {
		super(message);
	}

	public IllegalAnnotationError(final String message, final Throwable cause) {
		super(message, cause);
	}

	public IllegalAnnotationError(final Throwable cause) {
		super(cause);
	}
}
