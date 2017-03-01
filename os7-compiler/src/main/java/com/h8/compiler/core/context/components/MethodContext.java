package com.h8.compiler.core.context.components;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

public class MethodContext {
    @Getter
    private Method m;
    @Getter
    private ClassContext cCtx;
    @Setter @Getter
    private MethodAnnotationContext aCtx;

    public MethodContext(Method m, ClassContext cCtx) {
        this.m = m;
        this.cCtx = cCtx;
    }
}
