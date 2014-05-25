package nlink.win32;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Indicates that an interface represents the functions
 * exported from a DLL.
 * 
 * @author Kohsuke Kawaguchi
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DllClass {
    /**
     * Name of the DLL.
     *
     * If unspecified, class name is used as the library name.
     */
    String value() default "";
}
