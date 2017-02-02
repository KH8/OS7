package com.h8.compiler.core.context;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.processor.ClassFileLoader;
import com.h8.compiler.core.context.processor.StructureComponentFinder;
import com.h8.compiler.exception.CompilationFailedException;

public class CompilationContextBuilder extends CompilationContext {
    public CompilationContextBuilder() {}

    public void build(String directory)
            throws CompilationFailedException {
        this.buildForDirectory(directory)
                .buildClassList()
                .buildStructureComponents();
    }

    private CompilationContextBuilder buildForDirectory(String directory) {
        this.setDirectory(directory);
        Logger.log(CompilationContextBuilder.class, "Working directory: {1}", directory);
        return this;
    }

    private CompilationContextBuilder buildClassList()
            throws CompilationFailedException {
        new ClassFileLoader(this).process();
        return this;
    }

    private CompilationContextBuilder buildStructureComponents()
            throws CompilationFailedException {
        new StructureComponentFinder(this).process();
        return this;
    }
}
