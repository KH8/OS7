package com.h8.compiler.core.definitions.annotations.components.handlers;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContext;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.annotation.Annotation;

public interface ClassAnnotationHandler {
    void handle(CompilationContext context, ClassContext cCtx) throws CompilationFailedException;
}
