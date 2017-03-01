package com.h8.compiler.core.context.components;

import java.lang.reflect.Method;

public class MethodContextBuilder {
    public static MethodContext build(Method m, ClassContext cCtx) {
        MethodContext context = new MethodContext(m, cCtx);
        cCtx.getMethods().put(m.getName(), context);
        return context;
    }
}
