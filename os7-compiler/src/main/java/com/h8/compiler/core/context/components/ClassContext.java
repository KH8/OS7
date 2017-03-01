package com.h8.compiler.core.context.components;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ClassContext {
    @Getter
    private Class c;
    @Getter
    private ClassAnnotationContext aCtx;
    @Getter
    private Map<String, FieldContext> fields;
    @Getter
    private Map<String, MethodContext> methods;
    @Getter
    private Map<String, InstanceContext> instances;

    public ClassContext(Class c) {
        this.c = c;
        this.aCtx = new ClassAnnotationContext(c);
        this.fields = new HashMap<>();
        this.methods = new HashMap<>();
        this.instances = new HashMap<>();
    }
}