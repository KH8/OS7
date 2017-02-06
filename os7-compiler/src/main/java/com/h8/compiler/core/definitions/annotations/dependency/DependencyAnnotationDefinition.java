package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.core.definitions.Definition;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.os7.core.annotations.dependency.*;
import lombok.Getter;

public enum DependencyAnnotationDefinition implements Definition {
    INJECT(Inject.class, new InjectHandler()),
    INJECTABLE(Injectable.class, new InjectableHandler()),
    INSTANTIATE(Instantiate.class, new FieldInstanceBuilderHandler()),
    USE(Use.class, new FieldInstanceInjector());

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
}
