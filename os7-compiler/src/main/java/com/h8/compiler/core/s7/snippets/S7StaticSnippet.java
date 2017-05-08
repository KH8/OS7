package com.h8.compiler.core.s7.snippets;

import com.h8.compiler.common.FileReader;
import lombok.Getter;

import java.io.IOException;

public enum S7StaticSnippet implements S7Snippet {
    POINTER("s7_type_pointer.awl");

    private static final String RESOURCE_LOCATION = "snippets/static/";

    @Getter
    String snippet;

    S7StaticSnippet(String fileName) {
        try {
            snippet = new FileReader().readFile(RESOURCE_LOCATION + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}