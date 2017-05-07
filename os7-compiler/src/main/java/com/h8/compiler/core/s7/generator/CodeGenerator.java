package com.h8.compiler.core.s7.generator;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.processors.generator.InstanceDataBlockBuilder;
import com.h8.compiler.exception.CompilationFailedException;

public class CodeGenerator {
    private CompilationContext ctx;

    public void generate(CompilationContext context)
            throws CompilationFailedException {
        this.ctx = context;
        generateInstanceDataBlock();
    }

    private void generateInstanceDataBlock()
            throws CompilationFailedException {
        new InstanceDataBlockBuilder(ctx).process();
    }
}
