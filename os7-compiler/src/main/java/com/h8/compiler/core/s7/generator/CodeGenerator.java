package com.h8.compiler.core.s7.generator;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.processors.generator.ClassTypesBuilder;
import com.h8.compiler.core.processors.generator.FileEraser;
import com.h8.compiler.core.processors.generator.InstanceDataBlockBuilder;
import com.h8.compiler.core.processors.generator.StaticTypesBuilder;
import com.h8.compiler.exception.CompilationFailedException;

public class CodeGenerator {
    private CompilationContext ctx;

    public void generate(CompilationContext context)
            throws CompilationFailedException {
        this.ctx = context;
        eraseFile();
        writeStaticTypes();
        writeClassTypes();
        generateInstanceDataBlock();
    }

    private void eraseFile()
            throws CompilationFailedException {
        new FileEraser(ctx).process();
    }

    private void writeStaticTypes()
            throws CompilationFailedException {
        new StaticTypesBuilder(ctx).process();
    }

    private void writeClassTypes()
            throws CompilationFailedException {
        new ClassTypesBuilder(ctx).process();
    }

    private void generateInstanceDataBlock()
            throws CompilationFailedException {
        new InstanceDataBlockBuilder(ctx).process();
    }
}
