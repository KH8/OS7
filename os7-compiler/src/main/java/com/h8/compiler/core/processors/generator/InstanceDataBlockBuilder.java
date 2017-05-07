package com.h8.compiler.core.processors.generator;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.processors.AbstractProcessor;
import com.h8.compiler.core.s7.snippets.S7DynamicSnippet;
import com.h8.compiler.core.s7.snippets.SnippetFactory;
import com.h8.compiler.core.s7.snippets.SnippetParameter;
import com.h8.compiler.exception.CompilationFailedException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InstanceDataBlockBuilder extends AbstractProcessor {
    public InstanceDataBlockBuilder(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        SnippetParameter sp = SnippetParameter.nestedParameters();
        sp.put("NAME", SnippetParameter.singleParameters("TEST_NAME"));
        sp.put("VERSION", SnippetParameter.singleParameters("TEST_VERSION"));

        String result = new SnippetFactory().create(S7DynamicSnippet.DATA_BLOCK, sp);

        BufferedWriter output;
        try {
            output = new BufferedWriter(new FileWriter(context.getOutput()));
            output.write(result);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
