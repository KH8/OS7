package com.h8.compiler.core.context.components;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

public class FieldContext {
    @Getter
    private Field f;
    @Getter
    private ClassContext cCtx;
    @Setter @Getter
    private FieldAnnotationContext aCtx;

    public FieldContext(Field f, ClassContext cCtx) {
        this.f = f;
        this.cCtx = cCtx;
    }
}
