package com.h8.compiler.core.processors;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContextBuilder;
import com.h8.compiler.exception.CompilationFailedException;

public class ClassContextProcessor extends AbstractProcessor {
    public ClassContextProcessor(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        for (Class c : context.getClasses()) {
            context.get_classes().add(ClassContextBuilder.build(context, c));
        }
    }
}
