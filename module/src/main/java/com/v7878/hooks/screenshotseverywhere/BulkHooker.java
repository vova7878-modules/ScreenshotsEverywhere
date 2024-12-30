package com.v7878.hooks.screenshotseverywhere;

import static com.v7878.hooks.screenshotseverywhere.Main.TAG;
import static com.v7878.unsafe.Reflection.getDeclaredExecutables;

import android.util.Log;

import com.v7878.vmtools.Hooks;
import com.v7878.vmtools.Hooks.HookTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BulkHooker {
    public record HookElement(
            HookTransformer impl, String method, String ret, String... args) {
    }

    private final Map<String, List<HookElement>> hooks = new HashMap<>();

    public void add(HookTransformer impl, String clazz, String method, String ret, String... args) {
        hooks.computeIfAbsent(clazz, unused -> new ArrayList<>())
                .add(new HookElement(impl, method, ret, args));
    }

    private static String toString(String clazz, HookElement element) {
        var args = element.args();
        return element.ret() + " " + clazz + "." + element.method() +
                ((args == null || args.length == 0) ? "()" : Arrays.stream(args)
                        .collect(Collectors.joining(",", "(", ")")));
    }

    public void apply(ClassLoader loader) {
        for (var entry : hooks.entrySet()) {
            Class<?> clazz;
            try {
                clazz = Class.forName(entry.getKey(), true, loader);
            } catch (ClassNotFoundException ex) {
                Log.e(TAG, String.format("Class %s not found", entry.getKey()));
                continue;
            }
            var executables = getDeclaredExecutables(clazz);
            for (var element : entry.getValue()) {
                var executable = Utils.searchExecutable(executables,
                        element.method(), element.ret(), element.args());
                if (executable != null) {
                    Hooks.hook(executable, Hooks.EntryPointType.DIRECT,
                            element.impl(), Hooks.EntryPointType.DIRECT);
                } else {
                    Log.e(TAG, String.format("Method %s not found",
                            toString(entry.getKey(), element)));
                }
            }
        }
    }
}
