package com.h8.compiler.core.context;

import com.h8.compiler.core.definitions.Definition;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class Instance {
    private String name;
    private Class c;
    private List<Annotation> annotations = new ArrayList<>();
    private Definition definition;
    private List<Instance> fields = new ArrayList<>();
    private boolean fieldsInstantiated;
    private List<Instance> injected = new ArrayList<>();

    public Instance getFieldByName(String name) {
        return getInstanceFromListByName(fields, name);
    }

    public Instance getInjectedByName(String name) {
        return getInstanceFromListByName(injected, name);
    }

    private Instance getInstanceFromListByName(List<Instance> list, String name) {
        Optional<Instance> result = list.stream().filter(f -> f.getName().equals(name)).findFirst();
        return result.isPresent() ? result.get() : null;
    }

}
