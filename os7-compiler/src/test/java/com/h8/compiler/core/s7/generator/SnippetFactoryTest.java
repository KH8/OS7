package com.h8.compiler.core.s7.generator;

import com.h8.compiler.core.s7.snippets.S7DynamicSnippet;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnippetFactoryTest {
    @Test
    public void snippetWithSingleParameterCanBeCreated() throws Exception {
        SnippetParameter sp = SnippetParameter.nestedParameters();
        sp.put("NAME", SnippetParameter.singleParameters("TEST_NAME"));
        String result = new SnippetFactory().create(S7DynamicSnippet.TYPE, sp);
    }
}