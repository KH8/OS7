package com.h8.compiler.core.processors.generator;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.s7.generator.components.S7CodeComponents;
import com.h8.compiler.core.s7.generator.components.S7DataBlock;
import com.h8.compiler.core.s7.snippets.S7DynamicSnippet;
import com.h8.compiler.core.s7.snippets.SnippetFactory;

public class InstanceDataBlockBuilder extends OutputFileWriter {
    public InstanceDataBlockBuilder(CompilationContext context) {
        super(context);
    }

    @Override
    protected String getContent() {
        String name = "INSTANCES";
        String title = "DB containing all component instances";
        String version = getVersion();

        S7DataBlock sb = new S7DataBlock(
                name, title, version, false, new S7CodeComponents<>(), new S7CodeComponents<>());

        return new SnippetFactory().create(S7DynamicSnippet.DATA_BLOCK, sb.toSnippetParameter());
    }
}
