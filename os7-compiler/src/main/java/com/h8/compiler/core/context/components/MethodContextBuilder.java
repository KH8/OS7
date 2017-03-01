package com.h8.compiler.core.context.components;

import java.lang.reflect.Method;

public class MethodContextBuilder {
    public static MethodContext build(Method m, ClassContext cCtx) {
        return new MethodContext(m, cCtx);
    }
}
