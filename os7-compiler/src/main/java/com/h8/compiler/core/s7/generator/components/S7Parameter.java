package com.h8.compiler.core.s7.generator.components;

import com.h8.compiler.core.s7.snippets.SnippetParameter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class S7Parameter implements S7CodeComponent {
    private String name;
    private S7Type type;
    private String comment;

    @Override
    public SnippetParameter toSnippetParameter() {
        return SnippetParameter.nestedParameters()
                .with("NAME", name)
                .with("TYPE", type.getName())
                .with("COMMENT", comment);
    }
}
