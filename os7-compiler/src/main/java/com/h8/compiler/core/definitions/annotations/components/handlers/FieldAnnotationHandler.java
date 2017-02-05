package com.h8.compiler.core.definitions.annotations.components.handlers;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface FieldAnnotationHandler {
    void handle(CompilationContext context, Annotation a, Instance i, Field f);
}
