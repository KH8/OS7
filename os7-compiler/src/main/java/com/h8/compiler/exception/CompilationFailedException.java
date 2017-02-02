package com.h8.compiler.exception;

public class CompilationFailedException extends Exception {
    public CompilationFailedException(String m) {
        super(m);
    }

    public CompilationFailedException(String m, Exception e) {
        super(m, e);
    }
}
