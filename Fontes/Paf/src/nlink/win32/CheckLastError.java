package nlink.win32;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Checks {@code GetLastError} after the invocation and throws
 * {@link Win32NLinkException} if it returns non-zero.
 * 
 * @author Kohsuke Kawaguchi
 */
@Target( { ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLastError {
}
