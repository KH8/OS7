package com.h8.compiler.core.processors.generator;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContext;
import com.h8.compiler.core.s7.generator.components.S7CodeComponents;
import com.h8.compiler.core.s7.generator.components.S7Type;
import com.h8.compiler.core.s7.snippets.S7DynamicSnippet;
import com.h8.compiler.core.s7.snippets.SnippetFactory;

public class ClassTypesBuilder extends OutputFileWriter {
    public ClassTypesBuilder(CompilationContext context) {
        super(context);
    }

    @Override
    protected String getContent() {
        String result = "";
        for (ClassContext c : context.getClasses().values()) {
            result += getTypeFromClassContext(c);
            result += getContentSeparator();
        }
        return result;
    }

    private String getTypeFromClassContext(ClassContext c) {
        String name = getTypeName(c);
        String version = getVersion();
        S7Type sb = new S7Type(name, version, new S7CodeComponents<>());
        return new SnippetFactory().create(S7DynamicSnippet.TYPE, sb.toSnippetParameter());
    }

    private String getTypeName(ClassContext c) {
        String className = c.getC().getSimpleName();
        return className.replaceAll("(.)(\\p{Upper})", "$1_$2").toUpperCase();
    }
}
