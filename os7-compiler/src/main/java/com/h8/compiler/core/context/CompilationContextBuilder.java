package com.h8.compiler.core.context;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.processor.*;
import com.h8.compiler.exception.CompilationFailedException;

public class CompilationContextBuilder extends CompilationContext {
    private static final Logger LOGGER = Logger.get(CompilationContextBuilder.class);

    public CompilationContextBuilder() {}

    public void build(String directory)
            throws CompilationFailedException {
        this.buildForDirectory(directory)
                .buildClassList()
                .buildComponentInstances()
                .buildComponentFieldInstances()
                .injectComponentInstancesToFields()
                .injectComponentFieldsInstancesToFields();
    }

    private CompilationContextBuilder buildForDirectory(String directory) {
        this.setDirectory(directory);
        LOGGER.log("Working directory: {1}", directory);
        return this;
    }

    private CompilationContextBuilder buildClassList()
            throws CompilationFailedException {
        new ClassFileProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder buildComponentInstances()
            throws CompilationFailedException {
        new StructureAnnotationProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder buildComponentFieldInstances()
            throws CompilationFailedException {
        new InstantiateAnnotationProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder injectComponentInstancesToFields()
            throws CompilationFailedException {
        new UseAnnotationProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder injectComponentFieldsInstancesToFields()
            throws CompilationFailedException {
        new InjectAnnotationProcessor(this).process();
        return this;
    }
}
