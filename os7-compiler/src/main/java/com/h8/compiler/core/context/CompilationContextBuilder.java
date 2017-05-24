package com.h8.compiler.core.context;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.config.CompilationConfiguration;
import com.h8.compiler.core.processors.ClassFileProcessor;
import com.h8.compiler.core.processors.components.structure.FieldClassFinder;
import com.h8.compiler.core.processors.components.structure.FieldInstanceBuilder;
import com.h8.compiler.core.processors.components.structure.StructureAnnotationProcessor;
import com.h8.compiler.core.processors.dependency.InjectAnnotationProcessor;
import com.h8.compiler.core.processors.dependency.InjectableAnnotationProcessor;
import com.h8.compiler.core.processors.dependency.UseAnnotationProcessor;
import com.h8.compiler.exception.CompilationFailedException;

import java.io.File;

public class CompilationContextBuilder extends CompilationContext {
    private static final Logger LOGGER = Logger.get(CompilationContextBuilder.class);

    private static final String OUTPUT_FILE_NAME = "/out/output.awl";

    public CompilationContextBuilder() {}

    public void build(String directory)
            throws CompilationFailedException {
        this.buildConfiguration()
                .buildForDirectory(directory)
                .buildOutputFile()
                .buildClassContexts()
                .buildFieldClassContexts()
                .processStructureAnnotations()
                .processInstantiateAnnotations()
                .processUseAnnotations()
                .processInjectAnnotations()
                .processInjectableAnnotations();
    }

    private CompilationContextBuilder buildConfiguration()
            throws CompilationFailedException {
        this.setConfiguration(CompilationConfiguration.getConfiguration(null));
        return this;
    }

    private CompilationContextBuilder buildForDirectory(String directory) {
        this.setDirectory(directory);
        LOGGER.log("Working directory: {1}", directory);
        return this;
    }

    private CompilationContextBuilder buildOutputFile() {
        File output = new File(this.getDirectory() + OUTPUT_FILE_NAME);
        if (output.getParentFile().mkdirs()) {
            LOGGER.log("Directory created: {1}", output.getParentFile().getAbsolutePath());
        }
        this.setOutput(output);
        LOGGER.log("Output file created: {1}", output.getAbsolutePath());
        return this;
    }

    private CompilationContextBuilder buildClassContexts()
            throws CompilationFailedException {
        new ClassFileProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder buildFieldClassContexts()
            throws CompilationFailedException {
        new FieldClassFinder(this).process();
        return this;
    }

    private CompilationContextBuilder processStructureAnnotations()
            throws CompilationFailedException {
        new StructureAnnotationProcessor(this).process();
        return this;
    }

    private CompilationContextBuilder processInstantiateAnnotations()
            throws CompilationFailedException {
        new FieldInstanceBuilder(this).process();
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
