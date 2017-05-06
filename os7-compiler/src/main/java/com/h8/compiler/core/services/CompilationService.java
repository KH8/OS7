package com.h8.compiler.core.services;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.CompilationContextBuilder;
import com.h8.compiler.core.s7.generator.CodeGenerator;
import com.h8.compiler.exception.CompilationFailedException;

public class CompilationService {
    private static Logger LOGGER = Logger.get(CompilationService.class);

    private String directory;
    private CompilationContext ctx;

    public void compile(String directory) {
        this.directory = directory;
        buildContext();
        buildCode();
    }

    private void buildContext() {
        try {
            CompilationContextBuilder builder = new CompilationContextBuilder();
            builder.build(directory);
            ctx = builder;
        } catch (CompilationFailedException e) {
            LOGGER.log("Compilation context build failed");
            e.printStackTrace();
        }
    }

    private void buildCode() {
        new CodeGenerator().generate(ctx);
    }
}
