package com.v7878.hooks.screenshotseverywhere;

import static com.v7878.hooks.screenshotseverywhere.Main.TAG;
import static com.v7878.unsafe.Reflection.getDeclaredMethod;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.util.Log;

import com.v7878.r8.annotations.DoNotObfuscate;
import com.v7878.r8.annotations.DoNotShrink;
import com.v7878.vmtools.Hooks;
import com.v7878.vmtools.Hooks.EntryPointType;

import java.lang.reflect.Method;

@SuppressLint("PrivateApi")
public class DevicePolicyManagerServiceHook {
    private static final String DEVICE_POLICY = "com.android.server.devicepolicy.DevicePolicyManagerService";

    public static void init(ClassLoader loader) throws Throwable {
        Class<?> policy = Class.forName(DEVICE_POLICY, true, loader);
        {
            Method target = getDeclaredMethod(policy, "getScreenCaptureDisabled",
                    ComponentName.class, int.class, boolean.class);
            Method hooker = getDeclaredMethod(DevicePolicyManagerServiceHook.class,
                    "getScreenCaptureDisabled", Object.class,
                    ComponentName.class, int.class, boolean.class);
            Hooks.hook(target, hooker, EntryPointType.DIRECT);
        }
        {
            Method target = getDeclaredMethod(policy, "setScreenCaptureDisabled",
                    ComponentName.class, String.class, boolean.class, boolean.class);
            Method hooker = getDeclaredMethod(DevicePolicyManagerServiceHook.class,
                    "setScreenCaptureDisabled", Object.class, ComponentName.class,
                    String.class, boolean.class, boolean.class);
            Hooks.hook(target, hooker, EntryPointType.DIRECT);
        }

        Log.w(TAG, "DevicePolicyManagerServiceHook");
    }

    @SuppressWarnings("unused")
    @DoNotShrink
    @DoNotObfuscate
    private static boolean getScreenCaptureDisabled(
            Object /* DevicePolicyManagerService */ thiz,
            ComponentName who, int userHandle, boolean parent) {
        return false;
    }

    @SuppressWarnings("unused")
    @DoNotShrink
    @DoNotObfuscate
    private static void setScreenCaptureDisabled(
            Object /* DevicePolicyManagerService */ thiz, ComponentName who,
            String callerPackage, boolean disabled, boolean parent) {
        // nop
    }
}
