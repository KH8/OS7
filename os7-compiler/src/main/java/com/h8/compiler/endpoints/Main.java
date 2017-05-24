package com.h8.compiler.endpoints;

import com.h8.compiler.core.services.CompilationService;
import com.h8.compiler.core.services.WelcomeService;

public class Main {
    public static void main(String[] args) {
        new WelcomeService().logWelcomeMessage();
        String directory = getWorkingDirectory(args);
        new CompilationService().compile(directory);
    }

    private static String getWorkingDirectory(String[] args) {
        return args != null && args.length > 0 ? args[0] : System.getProperty("user.dir");
    }
}
