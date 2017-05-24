package com.h8.compiler.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Class c;

    private Logger(Class c) {
        this.c = c;
    }

    public static Logger get(Class c) {
        return new Logger(c);
    }

    public void log(String message, Object... args) {
        String formattedMessage = StringFormatter.format(message, args);
        log(formattedMessage);
    }

    public void log(String message) {
        Date date = new Date();
        print("{1} <p[{2}]/> : {3}", DATE_FORMAT.format(date), c.getSimpleName(), message);
    }

    public void printLineSeparator() {
        print(System.getProperty("line.separator"));
    }

    public void print(String message, Object... args) {
        System.out.println(StringFormatter.format(message, args));
    }
}
