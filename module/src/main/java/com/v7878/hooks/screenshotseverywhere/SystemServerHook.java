package com.v7878.hooks.screenshotseverywhere;

import static com.v7878.hooks.screenshotseverywhere.Main.TAG;

import android.util.Log;

public class SystemServerHook {
    public static void init(ClassLoader loader) throws Throwable {
        Log.w(TAG, "loader: " + loader);

        var hooks = new BulkHooker();
        HookList.init(hooks, true);
        hooks.apply(loader);
    }
}
