package com.h8.compiler.core.context.processor;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.core.definitions.annotations.dependency.DependencyAnnotationDefinition;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Inject;

public class InjectAnnotationProcessor extends AbstractProcessor {
    public InjectAnnotationProcessor(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        injectFieldsWithExistingInstances();
    }

    private void injectFieldsWithExistingInstances()
            throws CompilationFailedException {
        iterateThroughAllInstanceClassFields((i, f)  -> {
            Inject a = f.getAnnotation(Inject.class);
            if (a != null) {
                FieldAnnotationHandler h = DependencyAnnotationDefinition.INJECT.getHandler();
                h.handle(context, a, i, f);
            }
        });
    }
}
