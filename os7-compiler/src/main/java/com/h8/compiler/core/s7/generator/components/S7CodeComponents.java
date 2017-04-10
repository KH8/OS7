package com.h8.compiler.core.s7.generator.components;

import com.h8.compiler.core.s7.snippets.SnippetParameter;

import java.util.ArrayList;

public class S7CodeComponents<T extends S7CodeComponent> extends ArrayList<T> implements S7CodeComponent {
    public SnippetParameter toSnippetParameter() {
        SnippetParameter sp = SnippetParameter.nestedParameters();
        Integer i = 0;
        for (S7CodeComponent c : this) {
            sp.with(i.toString(), c.toSnippetParameter());
            i++;
        }
        return sp;
    }
}
