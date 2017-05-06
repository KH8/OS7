package com.h8.compiler.endpoints;

import com.h8.compiler.core.services.CompilationService;

public class Main {
    public static void main(String[] args) {
        String directory = getWorkingDirectory(args);
        new CompilationService().compile(directory);
    }

    private static String getWorkingDirectory(String[] args) {
        return args != null && args.length > 0 ? args[0] : System.getProperty("user.dir");
    }
}
