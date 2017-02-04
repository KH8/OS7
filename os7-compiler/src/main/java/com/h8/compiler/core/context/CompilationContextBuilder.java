package com.h8.compiler.core.context;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.processor.ClassFileProcessor;
import com.h8.compiler.core.context.processor.ComponentDependenciesProcessor;
import com.h8.compiler.core.context.processor.ComponentStructureProcessor;
import com.h8.compiler.exception.CompilationFailedException;

public class CompilationContextBuilder extends CompilationContext {
    private static final Logger LOGGER = Logger.get(CompilationContextBuilder.class);

    public CompilationContextBuilder() {}

    public void build(String directory)
            throws CompilationFailedException {
        this.buildForDirectory(directory)
                .buildClassList()
                .buildStructureComponents()
                .buildStructureComponentDependencies();
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

    private CompilationContextBuilder buildStructureComponents()
            throws CompilationFailedException {
        new ComponentStructureProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder buildStructureComponentDependencies()
            throws CompilationFailedException {
        new ComponentDependenciesProcessor(this).process();
        return this;
    }
}
