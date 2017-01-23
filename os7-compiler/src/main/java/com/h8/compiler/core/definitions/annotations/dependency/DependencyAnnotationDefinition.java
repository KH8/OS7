package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.core.definitions.Definition;
import com.h8.os7.core.annotations.dependency.*;

public enum DependencyAnnotationDefinition implements Definition {
    INJECT(Inject.class),
    INJECTABLE(Injectable.class),
    INSTANTIATE(Instantiate.class),
    RUNNER(Runner.class),
    USE(Use.class);

    Class c;

    DependencyAnnotationDefinition(Class c) {
        this.c = c;
    }

    @Override
    public Class getDefinedClass() {
        return this.c;
    }
}
