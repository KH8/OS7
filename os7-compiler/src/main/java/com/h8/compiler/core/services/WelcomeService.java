package com.h8.compiler.core.services;

import com.h8.compiler.common.StringFormatter;

public class WelcomeService {
    public void logWelcomeMessage() {
        String version = "1.0-SNAPSHOT";
        System.out.println(System.getProperty("line.separator"));
        System.out.println(StringFormatter.format("Welcome to <gOS7 Compiler/> project."));
        System.out.println(StringFormatter.format("version: <g{1}/>", version));
        System.out.println(System.getProperty("line.separator"));
    }
}
