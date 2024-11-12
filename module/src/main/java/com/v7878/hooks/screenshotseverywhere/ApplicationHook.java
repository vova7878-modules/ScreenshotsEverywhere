package com.v7878.hooks.screenshotseverywhere;

import static com.v7878.hooks.screenshotseverywhere.Main.TAG;

import android.util.Log;

import com.v7878.r8.annotations.DoNotShrink;
import com.v7878.zygisk.ZygoteLoader;

public class ApplicationHook {
    private static void unknown_package(String package_name) {
        Log.e(TAG, "Unknown package: " + package_name);
    }

    @DoNotShrink
    public static void init(String package_name, ClassLoader loader) throws Throwable {
        Log.w(TAG, "loader: " + loader);
        Log.w(TAG, "start: " + package_name);
        if (package_name.equals(ZygoteLoader.PACKAGE_SYSTEM_SERVER)) {
            WindowStateHook.init(loader);
            DevicePolicyManagerServiceHook.init(loader);
        } else {
            unknown_package(package_name);
        }
        Log.w(TAG, "end: " + package_name);
    }
}
