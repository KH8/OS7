package com.h8.compiler.core.s7.snippets;

import org.junit.Test;

import static org.junit.Assert.*;

public class S7DynamicSnippetTest {
    @Test
    public void typeSnippetFileCanBeRead() {
        S7DynamicSnippet type = S7DynamicSnippet.TYPE;
        assertNotNull(type.getSnippet());
    }

    @Test
    public void dataBlockSnippetFileCanBeRead() {
        S7DynamicSnippet dataBlock = S7DynamicSnippet.DATA_BLOCK;
        assertNotNull(dataBlock.getSnippet());
    }
}