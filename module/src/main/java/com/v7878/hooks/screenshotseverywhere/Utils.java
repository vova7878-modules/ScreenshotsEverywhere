package com.v7878.hooks.screenshotseverywhere;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

public class Utils {
    public static Field searchField(Field[] fields, String name) {
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }

    private static String clname(Class<?> clazz) {
        var component = clazz.getComponentType();
        if (component != null) {
            return clname(component) + "[]";
        }
        return clazz.getName();
    }

    private static String name(Executable executable) {
        if (executable instanceof Method m) {
            return m.getName();
        }
        assert executable instanceof Constructor<?>;
        return Modifier.isStatic(executable.getModifiers()) ? "<clinit>" : "<init>";
    }

    private static String ret(Executable executable) {
        if (executable instanceof Method m) {
            return clname(m.getReturnType());
        }
        assert executable instanceof Constructor<?>;
        return clname(void.class);
    }

    private static String[] args(Executable executable) {
        return Stream.of(executable.getParameterTypes())
                .map(Utils::clname).toArray(String[]::new);
    }

    public static Executable searchExecutable(Executable[] executables, String name, String ret, String... args) {
        for (var e : executables) {
            if (name.equals(name(e)) &&
                    ret.equals(ret(e)) &&
                    Arrays.equals(args(e), args)) {
                return e;
            }
        }
        return null;
    }
}
