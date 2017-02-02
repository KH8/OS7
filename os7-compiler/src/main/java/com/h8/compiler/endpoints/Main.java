package com.h8.compiler.endpoints;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContextBuilder;
import com.h8.compiler.exception.CompilationFailedException;

public class Main {

    public static void main(String[] args) {
        String directory = getWorkingDirectory(args);
        buildContext(directory);
    }

    private static String getWorkingDirectory(String[] args) {
        return args != null && args.length > 0 ? args[0] : System.getProperty("user.dir");
    }

    private static void buildContext(String directory) {
        try {
            new CompilationContextBuilder().build(directory);
        } catch (CompilationFailedException e) {
            Logger.log(Main.class, "Compilation context build failed");
            e.printStackTrace();
        }
    }
}
