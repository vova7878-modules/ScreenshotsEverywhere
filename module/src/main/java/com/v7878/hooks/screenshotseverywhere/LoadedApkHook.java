package com.v7878.hooks.screenshotseverywhere;

import static com.v7878.unsafe.Reflection.fieldOffset;
import static com.v7878.unsafe.Reflection.getDeclaredField;
import static com.v7878.unsafe.Reflection.getDeclaredMethod;
import static com.v7878.unsafe.Reflection.unreflect;
import static com.v7878.unsafe.Utils.nothrows_run;

import android.annotation.SuppressLint;

import com.v7878.r8.annotations.DoNotObfuscate;
import com.v7878.r8.annotations.DoNotShrink;
import com.v7878.unsafe.AndroidUnsafe;
import com.v7878.vmtools.Hooks;
import com.v7878.vmtools.Hooks.EntryPointType;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@SuppressLint("PrivateApi")
public class LoadedApkHook {
    private static final String LOADED_APK = "android.app.LoadedApk";
    private static final Class<?> LOADED_APK_CLASS = nothrows_run(() -> Class.forName(LOADED_APK));
    private static final MethodHandle getPackageName = unreflect(getDeclaredMethod(LOADED_APK_CLASS, "getPackageName"));
    private static final long CLASS_LOADER_OFFSET = fieldOffset(getDeclaredField(LOADED_APK_CLASS, "mClassLoader"));

    public static void init() throws Throwable {
        Method target = getDeclaredMethod(LOADED_APK_CLASS, "createOrUpdateClassLoaderLocked", List.class);
        Method hooker = getDeclaredMethod(LoadedApkHook.class, "createOrUpdateClassLoaderLocked", Object.class, Object.class);
        Hooks.hookSwap(target, EntryPointType.CURRENT, hooker, EntryPointType.DIRECT);
    }

    @SuppressWarnings({"unused", "InfiniteRecursion"})
    @DoNotShrink
    @DoNotObfuscate
    private static void createOrUpdateClassLoaderLocked(
            Object /* LoadedApk */ thiz, Object /* List<String> */ addedPaths) throws Throwable {
        // Call original method
        createOrUpdateClassLoaderLocked(thiz, addedPaths);

        Objects.requireNonNull(thiz);
        String package_name = (String) getPackageName.invoke(thiz);
        ClassLoader loader = (ClassLoader) AndroidUnsafe.getObject(thiz, CLASS_LOADER_OFFSET);

        ApplicationHook.init(package_name, loader);
    }
}
