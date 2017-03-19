package com.h8.compiler.core.s7.generator;

import com.h8.compiler.core.s7.snippets.S7DynamicSnippet;

public class SnippetFactory {
    public String create(S7DynamicSnippet snippet, SnippetParameter params) {
        String s = snippet.getSnippet();
        for (String key : params.keySet()) {
            SnippetParameter sp = params.get(key);
            s = s.replace("$" + key, sp.getValue());
        }
        return s;
    }
}
