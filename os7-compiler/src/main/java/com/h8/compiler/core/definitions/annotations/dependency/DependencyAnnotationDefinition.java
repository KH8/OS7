package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.Definition;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.*;

import java.lang.reflect.Field;

public enum DependencyAnnotationDefinition implements Definition {
    INJECT(Inject.class, new InjectHandler()),
    INJECTABLE(Injectable.class, new InjectableHandler()),
    INSTANTIATE(Instantiate.class, new InstantiateHandler()),
    USE(Use.class, new UseHandler());

    Class c;

    DependencyHandler dh;

    DependencyAnnotationDefinition(Class c, DependencyHandler dh) {
        this.c = c;
        this.dh = dh;
    }

    @Override
    public Class getDefinedClass() {
        return this.c;
    }

    public void handle(CompilationContext context, Instance i, Field f)
            throws CompilationFailedException {
        dh.handle(context, i, f);
    }
}
