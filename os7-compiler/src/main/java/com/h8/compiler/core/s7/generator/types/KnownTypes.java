package com.h8.compiler.core.s7.generator.types;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public enum KnownTypes {
    BOOL(Boolean.class),
    INT(Integer.class);

    private Set<Class> classes;

    KnownTypes(Class... classes) {
        this.classes = new HashSet<>(Arrays.asList(classes));
    }

    public static boolean isKnownType(Class c) {
        return getKnownType(c) != null;
    }

    public static KnownTypes getKnownType(Class c) {
        Optional<KnownTypes> result = Arrays.stream(values())
                .filter(t -> t.classes.contains(c))
                .findFirst();
        return result.isPresent() ? result.get() : null;
    }
}
