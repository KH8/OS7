package com.h8.compiler.core.s7.generator;

import com.h8.compiler.core.context.CompilationContext;

import java.io.File;

public class CodeGenerator {
    private File output;
    private CodeGeneratorParameters parameters;

    public CodeGenerator(File output) {
        this.output = output;
        this.parameters = CodeGeneratorParameters.get();
    }

    public void generate(CompilationContext context) {

    }
}
