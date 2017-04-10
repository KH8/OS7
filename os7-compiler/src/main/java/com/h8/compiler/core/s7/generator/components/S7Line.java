package com.h8.compiler.core.s7.generator.components;

import com.h8.compiler.core.s7.snippets.SnippetParameter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class S7Line implements S7CodeComponent {
    private S7Operation operation;
    private String parameter;
    private String comment;

    @Override
    public SnippetParameter toSnippetParameter() {
        return SnippetParameter.nestedParameters()
                .with("OPERATION", operation.toSnippetParameter())
                .with("PARAMETER", parameter)
                .with("COMMENT", comment);
    }
}
