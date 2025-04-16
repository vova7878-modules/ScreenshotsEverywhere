package com.v7878.hooks.screenshotseverywhere;

public class HookList {
    public static void init(BulkHooker hooks, boolean system_server) {
        hooks.addExact(HTF.FALSE, "com.android.server.devicepolicy.DevicePolicyManagerService", "getScreenCaptureDisabled", "boolean", "android.content.ComponentName", "int", "boolean");
        hooks.addExact(HTF.FALSE, "com.android.server.devicepolicy.DevicePolicyManagerService", "getScreenCaptureDisabled", "boolean", "android.content.ComponentName", "int");

        hooks.addExact(HTF.NOP, "com.android.server.devicepolicy.DevicePolicyManagerService", "setScreenCaptureDisabled", "void", "android.content.ComponentName", "java.lang.String", "boolean", "boolean");
        hooks.addExact(HTF.NOP, "com.android.server.devicepolicy.DevicePolicyManagerService", "setScreenCaptureDisabled", "void", "android.content.ComponentName", "boolean", "boolean");
        hooks.addExact(HTF.NOP, "com.android.server.devicepolicy.DevicePolicyManagerService", "setScreenCaptureDisabled", "void", "android.content.ComponentName", "boolean");
        hooks.addExact(HTF.NOP, "com.android.server.devicepolicy.DevicePolicyManagerService", "setScreenCaptureDisabled", "void", "int");

        hooks.addExact(HTF.TRUE, "com.android.server.devicepolicy.DevicePolicyCacheImpl", "isScreenCaptureAllowed", "boolean", "int");

        hooks.addExact(HTF.FALSE, "com.android.server.wm.WindowState", "isSecureLocked", "boolean");

        hooks.addExact(HTF.NOP, "com.android.server.wm.WindowState", "setSecureLocked", "void", "boolean");
        hooks.addExact(HTF.NOP, "com.android.server.wm.WindowStateAnimator", "setSecureLocked", "void", "boolean");
        hooks.addExact(HTF.NOP, "com.android.server.wm.WindowSurfaceController", "setSecure", "void", "boolean");

        hooks.addExact(HTF.FALSE, "com.android.server.wm.WindowManagerServiceImpl", "notAllowCaptureDisplay", "boolean", "com.android.server.wm.RootWindowContainer", "int");
    }
}
