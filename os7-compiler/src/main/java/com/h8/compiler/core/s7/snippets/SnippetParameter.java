package com.h8.compiler.core.s7.snippets;

import lombok.Getter;

import java.util.HashMap;

public class SnippetParameter extends HashMap<String, SnippetParameter> {
    @Getter
    private boolean isNested = false;

    @Getter
    private String value;

    private SnippetParameter() {

    }

    public static SnippetParameter nestedParameters() {
        SnippetParameter sp = new SnippetParameter();
        sp.isNested = true;
        sp.value = null;
        return sp;
    }

    public static SnippetParameter singleParameters(String value) {
        SnippetParameter sp = new SnippetParameter();
        sp.isNested = false;
        sp.value = value;
        return sp;
    }
}
