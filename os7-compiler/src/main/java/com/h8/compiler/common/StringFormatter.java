package com.h8.compiler.common;

public class StringFormatter {


    public static String format(String message, Object... args) {
        String formattedMessage = applyArguments(message, args);
        return applyColors(formattedMessage);
    }

    private static String applyArguments(String message, Object... args) {
        String formattedMessage = message;
        int index = 1;
        for (Object a : args) {
            String placeHolder = "{" + index + "}";
            formattedMessage = formattedMessage.replace(placeHolder, a.toString());
            index++;
        }
        return formattedMessage;
    }

    private static String applyColors(String message) {
        String formattedMessage = message;
        for (StringColors sc : StringColors.values()) {
            formattedMessage = formattedMessage.replaceAll(sc.symbol, sc.ansiCode);
        }
        return formattedMessage;
    }

    private enum StringColors {
        ANSI_RESET("/>", "\u001B[0m"),
        ANSI_RED("<r", "\u001B[31m"),
        ANSI_GREEN("<g", "\u001B[32m"),
        ANSI_YELLOW("<y", "\u001B[33m"),
        ANSI_BLUE("<b", "\u001B[34m"),
        ANSI_PURPLE("<p", "\u001B[35m"),
        ANSI_CYAN("<c", "\u001B[36m");

        private final String symbol;
        private final String ansiCode;

        StringColors(String symbol, String ansiCode) {
            this.symbol = symbol;
            this.ansiCode = ansiCode;
        }
    }
}
