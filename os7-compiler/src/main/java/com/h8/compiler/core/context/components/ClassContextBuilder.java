package com.h8.compiler.core.context.components;

public class ClassContextBuilder {
    public static ClassContext build(Class c) {
        return new ClassContext(c);
    }
}
