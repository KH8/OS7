package com.h8.compiler.core.s7.generator;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.exception.CompilationFailedException;

import java.io.File;

public class CodeGenerator {
    private File output;

    public CodeGenerator(File output)
            throws CompilationFailedException {
        this.output = output;
    }

    public void generate(CompilationContext context) {

    }
}
