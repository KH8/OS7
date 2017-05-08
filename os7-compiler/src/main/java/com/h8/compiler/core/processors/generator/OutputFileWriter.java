package com.h8.compiler.core.processors.generator;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.processors.AbstractProcessor;
import com.h8.compiler.exception.CompilationFailedException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

abstract class OutputFileWriter extends AbstractProcessor {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    OutputFileWriter(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        BufferedWriter output;
        try {
            output = new BufferedWriter(new FileWriter(context.getOutput(), true));
            output.write(getContent());
            output.write(getContentSeparator());
            output.close();
        } catch (IOException e) {
            throw new CompilationFailedException("Could not write to file", e);
        }
    }

    protected abstract String getContent();

    private String getContentSeparator() {
        return LINE_SEPARATOR + LINE_SEPARATOR;
    }
}
