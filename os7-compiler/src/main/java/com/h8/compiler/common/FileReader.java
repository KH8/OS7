package com.h8.compiler.common;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public String getResourceFileName(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);
        return url != null ? url.getFile() : null;
    }

    public String readFile(String fileName)
            throws IOException {
        String resourceFileName = getResourceFileName(fileName);
        return resourceFileName != null ? readFile(resourceFileName, Charset.defaultCharset()) : null;
    }

    private String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
