package nlink.win32;

import nlink.CallConvention;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Indicates that a method is exported from a DLL.
 *
 * <p>
 * This annotation is only allowed on a method inside an interface
 * with {@link DllClass} annotation.
 *
 * @author Kohsuke Kawaguchi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DllMethod {
    /**
     * Exported function name.
     *
     * To specify a function via ordinal value, use the "#nnn" syntax
     * (such as "#15").
     *
     * <p>
     * If left unspecified, the method name is used for {@code GetProcAddress}.
     * (If a procedure of that name is not found, the runtime tries by
     * appending 'W' to it (because Win32 APIs have 'W' suffix to indicate
     * the Unicode version.)
     */
    String value() default "";

    /**
     * Call convention of the method.
     */
    CallConvention convention() default CallConvention.STDCALL;
}
