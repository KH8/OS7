package com.h8.compiler.core.processors.generator;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.processors.AbstractProcessor;
import com.h8.compiler.exception.CompilationFailedException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileEraser extends AbstractProcessor {
    public FileEraser(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        BufferedWriter output;
        try {
            output = new BufferedWriter(new FileWriter(context.getOutput()));
            output.close();
        } catch (IOException e) {
            throw new CompilationFailedException("Output file could not be erased", e);
        }
    }
}
