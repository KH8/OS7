package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.core.definitions.Definition;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.os7.core.annotations.dependency.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum DependencyAnnotationDefinition implements Definition {
    INJECT(Inject.class, new InjectableInstanceProvider()),
    INJECTABLE(Injectable.class, new InjectableInstanceInjector()),
    USE(Use.class, new ComponentInjector());

    Class c;

    @Getter
    FieldAnnotationHandler handler;

    DependencyAnnotationDefinition(Class c, FieldAnnotationHandler handler) {
        this.c = c;
        this.handler = handler;
    }

    @Override
    public Class getDefinedClass() {
        return this.c;
    }

    public static DependencyAnnotationDefinition getByClass(Class c) {
        DependencyAnnotationDefinition[] definitions = DependencyAnnotationDefinition.values();
        Optional<DependencyAnnotationDefinition> result = Arrays.stream(definitions)
                .filter(d -> d.getDefinedClass().equals(c))
                .findFirst();
        return result.isPresent() ? result.get() : null;
    }
}
