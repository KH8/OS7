package com.h8.compiler.core.processors.generator;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.s7.snippets.S7StaticSnippet;
import com.h8.compiler.core.s7.snippets.SnippetFactory;
import com.h8.compiler.core.s7.snippets.SnippetParameter;

public class StaticTypesBuilder extends OutputFileWriter {
    public StaticTypesBuilder(CompilationContext context) {
        super(context);
    }

    @Override
    protected String getContent() {
        SnippetParameter sp = SnippetParameter.nestedParameters();

        String version = getVersion();
        sp.put("VERSION", SnippetParameter.singleParameters(version));

        return new SnippetFactory().create(S7StaticSnippet.POINTER, sp);
    }
}
