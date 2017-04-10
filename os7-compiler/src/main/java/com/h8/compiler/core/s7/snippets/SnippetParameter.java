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

    public SnippetParameter with(String name, String value) {
        SnippetParameter sp = SnippetParameter.singleParameters(value);
        return this.with(name, sp);
    }

    public SnippetParameter with(String name, SnippetParameter sp) {
        this.put(name, sp);
        return sp;
    }
}
