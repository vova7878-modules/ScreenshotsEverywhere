package com.v7878.hooks.screenshotseverywhere;

import static com.v7878.hooks.screenshotseverywhere.Main.TAG;
import static com.v7878.unsafe.Reflection.getDeclaredMethod;

import android.annotation.SuppressLint;
import android.util.Log;

import com.v7878.r8.annotations.DoNotObfuscate;
import com.v7878.r8.annotations.DoNotShrink;
import com.v7878.vmtools.Hooks;
import com.v7878.vmtools.Hooks.EntryPointType;

import java.lang.reflect.Method;

@SuppressLint("PrivateApi")
public class WindowStateHook {
    private static final String WINDOW_STATE = "com.android.server.wm.WindowState";

    public static void init(ClassLoader loader) throws Throwable {
        Class<?> state = Class.forName(WINDOW_STATE, true, loader);
        {
            Method target = getDeclaredMethod(state, "isSecureLocked");
            Method hooker = getDeclaredMethod(WindowStateHook.class,
                    "isSecureLocked", Object.class);
            Hooks.hook(target, hooker, EntryPointType.DIRECT);
        }
        {
            Method target = getDeclaredMethod(state, "setSecureLocked", boolean.class);
            Method hooker = getDeclaredMethod(WindowStateHook.class,
                    "setSecureLocked", Object.class, boolean.class);
            Hooks.hook(target, hooker, EntryPointType.DIRECT);
        }

        Log.w(TAG, "WindowStateHook");
    }

    @SuppressWarnings("unused")
    @DoNotShrink
    @DoNotObfuscate
    private static boolean isSecureLocked(Object /* WindowState */ thiz) {
        return false;
    }

    @SuppressWarnings("unused")
    @DoNotShrink
    @DoNotObfuscate
    private static void setSecureLocked(Object /* WindowState */ thiz, boolean isSecure) {
        // nop
    }
}
