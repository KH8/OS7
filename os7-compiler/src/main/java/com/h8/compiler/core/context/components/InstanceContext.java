package com.h8.compiler.core.context.components;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class InstanceContext {
    public InstanceContext(String name, Class c) {
        this.id = InstanceIdGenerator.getNextId();
        this.name = name;
        this.c = c;
    }

    @Getter
    private String id;

    @Getter
    private String name;

    @Getter
    private Class c;

    @Getter
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private Map<String, InstanceContext> fields = new HashMap<>();

    @Getter
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private Map<String, InstanceContext> injected = new HashMap<>();

    @Setter @Getter
    private boolean fieldsInstantiated;

    public InstanceContext getFieldByName(String name) {
        return fields.get(name);
    }

    public InstanceContext getInjectedByName(String name) {
        return injected.get(name);
    }
}
