package com.v7878.hooks.screenshotseverywhere;

import static com.v7878.unsafe.invoke.EmulatedStackFrame.RETURN_VALUE_IDX;

import com.v7878.vmtools.Hooks.HookTransformer;

public class HTF {
    public static final HookTransformer NOP = (original, stack) -> { /* nop */ };
    public static final HookTransformer FALSE = (original, stack) -> {
        stack.accessor().setBoolean(RETURN_VALUE_IDX, false);
    };
    public static final HookTransformer TRUE = (original, stack) -> {
        stack.accessor().setBoolean(RETURN_VALUE_IDX, true);
    };

    public static HookTransformer return_constant(Object value) {
        return (original, stack) -> stack.accessor().setValue(RETURN_VALUE_IDX, value);
    }
}
