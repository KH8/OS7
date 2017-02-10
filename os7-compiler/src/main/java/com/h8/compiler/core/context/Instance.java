package com.h8.compiler.core.context;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Instance {
    public Instance(String name, Class c) {
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
    private Map<String, Instance> fields = new HashMap<>();

    @Getter
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private Map<String, Instance> injected = new HashMap<>();

    @Setter @Getter
    private boolean fieldsInstantiated;

    public Instance getFieldByName(String name) {
        return fields.get(name);
    }

    public Instance getInjectedByName(String name) {
        return injected.get(name);
    }
}
