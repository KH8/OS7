package com.h8.compiler.core.context.components.builders;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassAnnotationContext;
import com.h8.compiler.core.context.components.ClassAnnotations;
import com.h8.compiler.core.context.components.ClassContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassContextBuilder {
    public static void build(CompilationContext context, Class c) {
        ClassContext cCtx = new ClassContext(c);
        context.getClasses().put(c, cCtx);
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
        List<Field> fields = getInheritedFields(cCtx.getC());
        for (Field f : fields) {
            FieldContextBuilder.build(context, f, cCtx);
        }
    }

    private static List<Field> getInheritedFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        Collections.reverse(fields);
        return fields;
    }

    private static void buildMethodContexts(CompilationContext context, ClassContext cCtx) {
        for (Method m : cCtx.getC().getDeclaredMethods()) {
            MethodContextBuilder.build(context, m, cCtx);
        }
    }
}