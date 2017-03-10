package com.h8.compiler.core.context.components;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;

public class FieldContext {
    @Getter
    private Field f;
    @Setter @Getter
    private ClassContext cCtx;
    @Getter
    private ClassContext parentCtx;
    @Setter @Getter
    private FieldAnnotationContext aCtx;

    public FieldContext(Field f, ClassContext parentCtx) {
        this.f = f;
        this.parentCtx = parentCtx;
        this.aCtx = new FieldAnnotationContext(f);
    }
}
