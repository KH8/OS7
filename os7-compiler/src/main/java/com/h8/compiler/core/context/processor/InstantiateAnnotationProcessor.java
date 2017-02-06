package com.h8.compiler.core.context.processor;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.core.definitions.annotations.dependency.DependencyAnnotationDefinition;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Instantiate;

public class InstantiateAnnotationProcessor extends AbstractProcessor {
    public InstantiateAnnotationProcessor(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        instantiateFields();
    }

    private void instantiateFields()
            throws CompilationFailedException {
        int initialNumberOfInstances = context.getInstances().size();
        findAndInstantiateNewFields();
        if (context.getInstances().size() > initialNumberOfInstances) {
            instantiateFields();
        }
    }

    private void findAndInstantiateNewFields()
            throws CompilationFailedException {
        iterateThroughAllInstanceClassFields((i, f)  -> {
            if (i.isFieldsInstantiated()) return;
            Instantiate a = f.getAnnotation(Instantiate.class);
            if (a != null) {
                FieldAnnotationHandler h = DependencyAnnotationDefinition.INSTANTIATE.getHandler();
                h.handle(context, a, i, f);
            }
        });
    }
}
