package com.h8.compiler.core.definitions.annotations.components.handlers;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface FieldAnnotationHandler<T extends Annotation> {
    void handle(CompilationContext context, T a, Instance i, Field f) throws CompilationFailedException;
}
