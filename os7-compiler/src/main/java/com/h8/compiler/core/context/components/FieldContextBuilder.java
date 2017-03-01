package com.h8.compiler.core.context.components;

import com.h8.compiler.core.context.CompilationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class FieldContextBuilder {
    public static FieldContext build(CompilationContext context, Field f, ClassContext cCtx) {
        FieldContext fCtx = new FieldContext(f, cCtx);
        cCtx.getFields().put(f.getName(), fCtx);
        propagateAnnotations(context, fCtx);
        return fCtx;
    }

    private static void propagateAnnotations(CompilationContext context, FieldContext fCtx) {
        FieldAnnotationContext aCtx = fCtx.getACtx();
        propagateAnnotation(context, fCtx, aCtx.getInjectableAnnotation());
        propagateAnnotation(context, fCtx, aCtx.getInjectAnnotation());
        propagateAnnotation(context, fCtx, aCtx.getInstantiateAnnotation());
        propagateAnnotation(context, fCtx, aCtx.getUseAnnotation());
    }

    private static void propagateAnnotation(CompilationContext context, FieldContext fCtx, Annotation a) {
        FieldAnnotations annotations = context.getFieldAnnotations();
        if (a != null) {
            if (!annotations.containsKey(a.annotationType())) {
                annotations.put(a.annotationType(), new ArrayList<>());
            }
            annotations.get(a.annotationType()).add(fCtx);
        }
    }
}
