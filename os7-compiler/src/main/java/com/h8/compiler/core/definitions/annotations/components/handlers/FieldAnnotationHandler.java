package com.h8.compiler.core.definitions.annotations.components.handlers;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.FieldContext;
import com.h8.compiler.core.context.components.InstanceContext;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface FieldAnnotationHandler {
    void handle(CompilationContext context, FieldContext fCtx) throws CompilationFailedException;
}
