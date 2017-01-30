package com.h8.compiler.endpoints;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContextBuilder;

public class Main {

    public static void main(String[] args) {
        String wd = getWorkingDirectory(args);
        buildContext(wd);
    }

    private static String getWorkingDirectory(String[] args) {
        return args != null && args.length > 0 ? args[0] : System.getProperty("user.dir");
    }

    private static void buildContext(String workingDirectory) {
        Logger.log(Main.class, "Building compilation context:");
        CompilationContextBuilder builder = new CompilationContextBuilder();
        builder.buildForWorkingDirectory(workingDirectory);
    }
}
