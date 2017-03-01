package com.h8.compiler.core.context.components;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassContextBuilder {
    public static ClassContext build(Class c) {
        ClassContext context = new ClassContext(c);
        buildFieldContexts(c, context);
        buildMethodContexts(c, context);
        return context;
    }

    private static void buildFieldContexts(Class c, ClassContext context) {
        for (Field f : c.getDeclaredFields()) {
            FieldContextBuilder.build(f, context);
        }
    }

    private static void buildMethodContexts(Class c, ClassContext context) {
        for (Method m : c.getDeclaredMethods()) {
            MethodContextBuilder.build(m, context);
        }
    }
}