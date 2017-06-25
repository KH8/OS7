package com.h8.compiler.core.processors.generator;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContext;
import com.h8.compiler.core.context.components.FieldContext;
import com.h8.compiler.core.s7.generator.components.S7CodeComponents;
import com.h8.compiler.core.s7.generator.components.S7Parameter;
import com.h8.compiler.core.s7.generator.components.S7Type;
import com.h8.compiler.core.s7.snippets.S7DynamicSnippet;
import com.h8.compiler.core.s7.snippets.SnippetFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassTypesBuilder extends OutputFileWriter {
    public ClassTypesBuilder(CompilationContext context) {
        super(context);
    }

    @Override
    protected String getContent() {
        String result = "";
        for (ClassContext c : context.getClasses().values()) {
            if (checkIfClassShouldBeProcessed(c)) {
                result += getTypeFromClassContext(c);
                result += getContentSeparator();
            }
        }
        return result;
    }

    private boolean checkIfClassShouldBeProcessed(ClassContext c) {
        return !getTypeName(c).isEmpty() && !c.getFields().isEmpty();
    }

    private String getTypeFromClassContext(ClassContext c) {
        String name = getTypeName(c);
        String version = getVersion();
        S7Type sb = new S7Type(name, version, getTypeParameters(c));
        return new SnippetFactory().create(S7DynamicSnippet.TYPE, sb.toSnippetParameter());
    }

    private S7CodeComponents<S7Parameter> getTypeParameters(ClassContext c) {
        S7CodeComponents<S7Parameter> parameters = new S7CodeComponents<>();
        List<FieldContext> fields = new ArrayList<>(c.getFields().values());
        Collections.reverse(fields);
        for (FieldContext fc : fields) {
            String name = getName(fc.getF().getName());
            S7Type type = new S7Type("INT", "", new S7CodeComponents<>());
            S7Parameter p = new S7Parameter(name, type,
                    "Class field " + fc.getF().getName() + " of type: " + fc.getF().getType().getSimpleName());
            parameters.add(p);
        }
        return parameters;
    }

    private String getTypeName(ClassContext c) {
        return getName(c.getC().getSimpleName());
    }

    private String getName(String name) {
        return name.replaceAll("(\\p{Lower})(\\p{Upper})", "$1_$2").toUpperCase();
    }
}
