package com.h8.compiler.core.context.processor;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.core.definitions.annotations.dependency.DependencyAnnotationDefinition;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Use;

public class UseAnnotationProcessor extends AbstractProcessor {
    public UseAnnotationProcessor(CompilationContext context) {
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
            Use a = f.getAnnotation(Use.class);
            if (a != null) {
                FieldAnnotationHandler h = DependencyAnnotationDefinition.USE.getHandler();
                h.handle(context, a, i, f);
            }
        });
    }
}
