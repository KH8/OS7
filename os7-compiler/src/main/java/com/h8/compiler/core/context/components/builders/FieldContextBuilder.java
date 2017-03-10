package com.h8.compiler.core.context.components.builders;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContext;
import com.h8.compiler.core.context.components.FieldAnnotationContext;
import com.h8.compiler.core.context.components.FieldAnnotations;
import com.h8.compiler.core.context.components.FieldContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

class FieldContextBuilder {
    public static void build(CompilationContext context, Field f, ClassContext parentCtx) {
        FieldContext fCtx = new FieldContext(f, parentCtx);
        parentCtx.getFields().put(f.getName(), fCtx);
        propagateAnnotations(context, fCtx);
    }

    private static void propagateAnnotations(CompilationContext context, FieldContext fCtx) {
        FieldAnnotationContext aCtx = fCtx.getACtx();
        propagateAnnotation(context, fCtx, aCtx.getInjectableAnnotation());
        propagateAnnotation(context, fCtx, aCtx.getInjectAnnotation());
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
