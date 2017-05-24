package com.h8.compiler.core.services;

import com.h8.compiler.common.Logger;

public class WelcomeService {
    private static final Logger LOGGER = Logger.get(WelcomeService.class);

    public void logWelcomeMessage() {
        String version = "1.0-SNAPSHOT";
        LOGGER.printLineSeparator();
        LOGGER.print("Welcome to <gOS7 Compiler/> project.");
        LOGGER.print("version: <g{1}/>", version);
        LOGGER.printLineSeparator();
    }
}
