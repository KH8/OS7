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

    @Test
    public void snippetWithNestedParametersCanBeCreated() throws Exception {
        SnippetParameter p1 = SnippetParameter.nestedParameters();
        p1.put("NAME", SnippetParameter.singleParameters("TEST_NAME1"));
        p1.put("TYPE", SnippetParameter.singleParameters("TEST_TYPE1"));
        p1.put("COMMENT", SnippetParameter.singleParameters("TEST_COMMENT1"));

        SnippetParameter p2 = SnippetParameter.nestedParameters();
        p2.put("NAME", SnippetParameter.singleParameters("TEST_NAME2"));
        p2.put("TYPE", SnippetParameter.singleParameters("TEST_TYPE2"));
        p2.put("COMMENT", SnippetParameter.singleParameters("TEST_COMMENT2"));

        SnippetParameter parameters = SnippetParameter.nestedParameters();
        parameters.put("1", p1);
        parameters.put("2", p2);

        SnippetParameter sp = SnippetParameter.nestedParameters();
        sp.put("PARAMETERS", parameters);

        String result = new SnippetFactory().create(S7DynamicSnippet.TYPE, sp);
        assertTrue(result.contains("TEST_NAME1 : TEST_TYPE1; //TEST_COMMENT1"));
        assertTrue(result.contains("TEST_NAME2 : TEST_TYPE2; //TEST_COMMENT2"));
    }
}