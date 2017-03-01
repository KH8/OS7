package com.h8.compiler.core.context.components;

import java.lang.reflect.Field;

public class FieldContextBuilder {
    public static FieldContext build(Field f, ClassContext cCtx) {
        return new FieldContext(f, cCtx);
    }
}
