package com.h8.compiler.core.s7.generator.components;

import com.h8.compiler.core.s7.snippets.SnippetParameter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class S7Network implements S7CodeComponent {
    private String title;
    private S7CodeComponents<S7Parameter> lines;

    @Override
    public SnippetParameter toSnippetParameter() {
        return SnippetParameter.nestedParameters()
                .with("TITLE", title)
                .with("LINES", lines.toSnippetParameter());
    }
}
