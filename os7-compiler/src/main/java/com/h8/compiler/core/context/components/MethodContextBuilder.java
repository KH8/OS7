package com.h8.compiler.core.context.components;

import com.h8.compiler.core.context.CompilationContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MethodContextBuilder {
    public static void build(CompilationContext context, Method m, ClassContext cCtx) {
        MethodContext mCtx = new MethodContext(m, cCtx);
        cCtx.getMethods().put(m.getName(), mCtx);
        propagateAnnotations(context, mCtx);
    }

    private static void propagateAnnotations(CompilationContext context, MethodContext mCtx) {
        MethodAnnotationContext aCtx = mCtx.getACtx();
    }

    private static void propagateAnnotation(CompilationContext context, MethodContext mCtx, Annotation a) {
        MethodAnnotations annotations = context.getMethodAnnotations();
        if (a != null) {
            if (!annotations.containsKey(a.annotationType())) {
                annotations.put(a.annotationType(), new ArrayList<>());
            }
            annotations.get(a.annotationType()).add(mCtx);
        }
    }
}
