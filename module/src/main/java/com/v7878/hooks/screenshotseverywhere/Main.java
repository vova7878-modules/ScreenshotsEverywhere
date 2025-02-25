package com.v7878.hooks.screenshotseverywhere;

import android.util.Log;

import com.v7878.r8.annotations.DoNotObfuscate;
import com.v7878.r8.annotations.DoNotObfuscateType;
import com.v7878.r8.annotations.DoNotShrink;
import com.v7878.r8.annotations.DoNotShrinkType;
import com.v7878.zygisk.ZygoteLoader;

@DoNotObfuscateType
@DoNotShrinkType
public class Main {
    public static final String TAG = "SCREENSHOTS_EVERYWHERE";

    @SuppressWarnings({"unused", "ConfusingMainMethod"})
    @DoNotShrink
    @DoNotObfuscate
    public static void main() throws Throwable {
        Log.i(TAG, "Injected into " + ZygoteLoader.getPackageName());
        try {
            MethodAndArgsCallerHook.init();
        } catch (Throwable th) {
            Log.e(TAG, "Exception", th);
        }
        Log.i(TAG, "Done");
    }
}
