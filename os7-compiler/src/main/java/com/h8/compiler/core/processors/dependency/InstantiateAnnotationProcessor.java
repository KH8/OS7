package com.h8.compiler.core.processors.dependency;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Instantiate;

import java.lang.reflect.Field;

public class InstantiateAnnotationProcessor extends AbstractDependencyAnnotationProcessor<Instantiate> {
    public InstantiateAnnotationProcessor(CompilationContext context) {
        super(context, Instantiate.class);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        instantiateFields();
    }

    private void instantiateFields()
            throws CompilationFailedException {
        int initialNumberOfInstances = context.getInstances().size();
        super.process();
        if (context.getInstances().size() > initialNumberOfInstances) {
            instantiateFields();
        }
    }

    @Override
    protected void handleField(Instance i, Field f)
            throws CompilationFailedException {
        if (!i.isFieldsInstantiated()) {
            super.handleField(i, f);
        }
    }
}
