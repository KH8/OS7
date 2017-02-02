package com.h8.compiler.core.definitions.annotations.components.structure;

import com.h8.compiler.core.definitions.Definition;
import com.h8.os7.core.annotations.components.structure.*;

import java.lang.annotation.Annotation;

public enum StructureAnnotationDefinition implements Definition {
    COMPONENT(Component.class, a -> ((Component)a).value()),
    CONTROLLER(Controller.class, a -> ((Controller)a).value()),
    INTERFACE(Interface.class, a -> ((Interface)a).value());

    Class c;
    NameResolver nr;

    StructureAnnotationDefinition(Class c, NameResolver nr) {
        this.c = c;
        this.nr = nr;
    }

    @Override
    public Class getDefinedClass() {
        return this.c;
    }

    public String getName(Annotation a) {
        return this.nr.getName(a);
    }

    private interface NameResolver {
        String getName(Annotation a);
    }
}
