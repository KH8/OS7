package com.h8.compiler.core.s7.snippets;

import org.junit.Test;

import static org.junit.Assert.*;

public class S7StaticSnippetTest {
    @Test
    public void pointerSnippetFileCanBeRead() {
        S7StaticSnippet pointer = S7StaticSnippet.POINTER;
        assertNotNull(pointer.getSnippet());
    }
}