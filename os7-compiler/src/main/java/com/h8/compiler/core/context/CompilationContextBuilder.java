package com.h8.compiler.core.context;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.processors.*;
import com.h8.compiler.core.processors.components.structure.StructureAnnotationProcessor;
import com.h8.compiler.core.processors.dependency.InjectAnnotationProcessor;
import com.h8.compiler.core.processors.dependency.InjectableAnnotationProcessor;
import com.h8.compiler.core.processors.dependency.InstantiateAnnotationProcessor;
import com.h8.compiler.core.processors.dependency.UseAnnotationProcessor;
import com.h8.compiler.exception.CompilationFailedException;

public class CompilationContextBuilder extends CompilationContext {
    private static final Logger LOGGER = Logger.get(CompilationContextBuilder.class);

    public CompilationContextBuilder() {}

    public void build(String directory)
            throws CompilationFailedException {
        this.buildForDirectory(directory)
                .buildClassContexts()
                .processStructureAnnotations()
                .processInstantiateAnnotations()
                .processUseAnnotations()
                .processInjectAnnotations()
                .processInjectableAnnotations();
    }

    private CompilationContextBuilder buildForDirectory(String directory) {
        this.setDirectory(directory);
        LOGGER.log("Working directory: {1}", directory);
        return this;
    }

    private CompilationContextBuilder buildClassContexts()
            throws CompilationFailedException {
        new ClassFileProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder processStructureAnnotations()
            throws CompilationFailedException {
        new StructureAnnotationProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder processInstantiateAnnotations()
            throws CompilationFailedException {
        new InstantiateAnnotationProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder processUseAnnotations()
            throws CompilationFailedException {
        new UseAnnotationProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder processInjectAnnotations()
            throws CompilationFailedException {
        new InjectAnnotationProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder processInjectableAnnotations()
            throws CompilationFailedException {
        new InjectableAnnotationProcessor(this).process();
        return this;
    }
}
