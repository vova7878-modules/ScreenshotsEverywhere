package com.v7878.hooks.screenshotseverywhere;

import java.util.Collections;

public class HookList {
    public static void init(BulkHooker hooks, boolean system_server) {
        hooks.add(HTF.FALSE, "com.android.server.devicepolicy.DevicePolicyManagerService", "getScreenCaptureDisabled", "boolean", "android.content.ComponentName", "int", "boolean");
        hooks.add(HTF.FALSE, "com.android.server.devicepolicy.DevicePolicyManagerService", "getScreenCaptureDisabled", "boolean", "android.content.ComponentName", "int");

        hooks.add(HTF.NOP, "com.android.server.devicepolicy.DevicePolicyManagerService", "setScreenCaptureDisabled", "void", "android.content.ComponentName", "java.lang.String", "boolean", "boolean");
        hooks.add(HTF.NOP, "com.android.server.devicepolicy.DevicePolicyManagerService", "setScreenCaptureDisabled", "void", "android.content.ComponentName", "boolean", "boolean");
        hooks.add(HTF.NOP, "com.android.server.devicepolicy.DevicePolicyManagerService", "setScreenCaptureDisabled", "void", "android.content.ComponentName", "boolean");
        hooks.add(HTF.NOP, "com.android.server.devicepolicy.DevicePolicyManagerService", "setScreenCaptureDisabled", "void", "int");

        hooks.add(HTF.TRUE, "com.android.server.devicepolicy.DevicePolicyCacheImpl", "isScreenCaptureAllowed", "boolean", "int");

        hooks.add(HTF.FALSE, "com.android.server.wm.WindowState", "isSecureLocked", "boolean");
        hooks.add(HTF.FALSE, "com.android.server.wm.WindowManagerService", "isSecureLocked", "boolean", "com.android.server.wm.WindowState");

        hooks.add(HTF.NOP, "com.android.server.wm.WindowState", "setSecureLocked", "void", "boolean");
        hooks.add(HTF.NOP, "com.android.server.wm.WindowStateAnimator", "setSecureLocked", "void", "boolean");
        hooks.add(HTF.NOP, "com.android.server.wm.WindowSurfaceController", "setSecure", "void", "boolean");

        hooks.add(HTF.return_constant(Collections.emptyList()), "com.android.server.wm.WindowManagerService", "notifyScreenshotListeners", "java.util.List", "int");

        hooks.add(HTF.FALSE, "com.android.server.wm.WindowManagerServiceImpl", "notAllowCaptureDisplay", "boolean", "com.android.server.wm.RootWindowContainer", "int");
    }
}
