package com.h8.compiler.core.processors;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.exception.CompilationFailedException;

public class ClassReader extends AbstractProcessor {
    public ClassReader(CompilationContext context) {
        super(context);
    }

    @Override
    public void process() throws CompilationFailedException {
        iterateThroughClasses(this::readClass);
    }

    private void readClass(Class c) {

    }
}
