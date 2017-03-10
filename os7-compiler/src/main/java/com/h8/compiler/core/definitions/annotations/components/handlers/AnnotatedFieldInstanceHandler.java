package com.h8.compiler.core.definitions.annotations.components.handlers;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.FieldContext;
import com.h8.compiler.core.context.components.InstanceContext;
import com.h8.compiler.exception.CompilationFailedException;

public abstract class AnnotatedFieldInstanceHandler implements FieldAnnotationHandler {
    @Override
    public void handle(CompilationContext context, FieldContext fCtx)
            throws CompilationFailedException {
        for (InstanceContext iCtx : fCtx.getParentCtx().getInstances().values()) {
            handleFieldInstance(context, fCtx, iCtx);
        }
    }

    protected abstract void handleFieldInstance(CompilationContext context, FieldContext fCtx, InstanceContext iCtx)
            throws CompilationFailedException;
}
