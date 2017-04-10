package com.h8.compiler.core.s7.generator.components;

import com.h8.compiler.core.s7.snippets.SnippetParameter;

public enum S7Operation implements S7CodeComponent {
    CALL,
    L,
    T;

    @Override
    public SnippetParameter toSnippetParameter() {
        return SnippetParameter.singleParameters(this.name());
    }
}
