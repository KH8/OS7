package com.h8.compiler.core.s7.snippets;

import com.h8.compiler.common.FileReader;
import lombok.Getter;

import java.io.IOException;

public enum S7DynamicSnippet {
    TYPE("s7_type.awl"),
    DATA_BLOCK("s7_data_block.awl"),
    FUNCTION("s7_function.awl"),
    NETWORK("s7_network.awl");

    private static final String RESOURCE_LOCATION = "snippets/dynamic/";

    @Getter
    String snippet;

    S7DynamicSnippet(String fileName) {
        try {
            snippet = new FileReader().readFile(RESOURCE_LOCATION + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
