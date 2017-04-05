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
        assertTrue(result.contains("TYPE \"TEST_NAME\""));
    }

    @Test
    public void snippetWithMultipleParametersCanBeCreated() throws Exception {
        SnippetParameter sp = SnippetParameter.nestedParameters();
        sp.put("NAME", SnippetParameter.singleParameters("TEST_NAME"));
        sp.put("VERSION", SnippetParameter.singleParameters("TEST_VERSION"));
        String result = new SnippetFactory().create(S7DynamicSnippet.TYPE, sp);
        assertTrue(result.contains("TYPE \"TEST_NAME\""));
        assertTrue(result.contains("VERSION : TEST_VERSION"));
    }
}