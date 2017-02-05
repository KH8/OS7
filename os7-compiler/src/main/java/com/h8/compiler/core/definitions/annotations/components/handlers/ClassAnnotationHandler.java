package com.h8.compiler.core.definitions.annotations.components.handlers;

import com.h8.compiler.core.context.CompilationContext;

import java.lang.annotation.Annotation;

public interface ClassAnnotationHandler {
    void handle(CompilationContext context, Annotation a);
}
