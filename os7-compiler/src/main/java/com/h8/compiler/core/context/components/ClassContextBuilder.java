package com.h8.compiler.core.context.components;

import com.h8.compiler.core.context.CompilationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassContextBuilder {
    public static void build(CompilationContext context, Class c) {
        ClassContext cCtx = new ClassContext(c);
        context.getClasses().add(cCtx);
        propagateAnnotations(context, cCtx);
        buildFieldContexts(context, cCtx);
        buildMethodContexts(context, cCtx);
    }

    private static void propagateAnnotations(CompilationContext context, ClassContext cCtx) {
        ClassAnnotationContext aCtx = cCtx.getACtx();
        propagateAnnotation(context, cCtx, aCtx.getComponentAnnotation());
        propagateAnnotation(context, cCtx, aCtx.getControllerAnnotation());
        propagateAnnotation(context, cCtx, aCtx.getInterfaceAnnotation());
    }

    private static void propagateAnnotation(CompilationContext context, ClassContext cCtx, Annotation a) {
        ClassAnnotations annotations = context.getClassAnnotations();
        if (a != null) {
            if (!annotations.containsKey(a.annotationType())) {
                annotations.put(a.annotationType(), new ArrayList<>());
            }
            annotations.get(a.annotationType()).add(cCtx);
        }
    }

    private static void buildFieldContexts(CompilationContext context, ClassContext cCtx) {
        for (Field f : cCtx.getC().getDeclaredFields()) {
            FieldContextBuilder.build(context, f, cCtx);
        }
    }

    private static void buildMethodContexts(CompilationContext context, ClassContext cCtx) {
        for (Method m : cCtx.getC().getDeclaredMethods()) {
            MethodContextBuilder.build(context, m, cCtx);
        }
    }
}