package com.h8.compiler.core.context.components;

import java.lang.reflect.Field;

public class FieldContextBuilder {
    public static FieldContext build(Field f, ClassContext cCtx) {
        FieldContext context = new FieldContext(f, cCtx);
        cCtx.getFields().put(f.getName(), context);
        return context;
    }
}
