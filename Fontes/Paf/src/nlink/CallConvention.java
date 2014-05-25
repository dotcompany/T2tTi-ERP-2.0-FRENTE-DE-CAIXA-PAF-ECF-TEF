package nlink;

/**
 * Designates the calling convention.
 *
 * @author Kohsuke Kawaguchi
 */
public enum CallConvention {
    /**
     * C-style. Arguments are pushed from right to left, and
     * the caller is responsible for stack clean up.
     */
    CDECL,
    /**
     * Pascal-style. Arguments are pushed from left to right,
     * and the callee cleans up the stack.
     */
    STDCALL
}
