package com.h8.compiler.core.definitions.annotations.components.structure;

import com.h8.compiler.core.definitions.Definition;
import com.h8.os7.core.annotations.components.structure.*;
import lombok.Getter;

import java.lang.annotation.Annotation;

public enum StructureAnnotationDefinition implements Definition {
    COMPONENT(Component.class, a -> ((Component)a).value()),
    CONTROLLER(Controller.class, a -> ((Controller)a).value()),
    INTERFACE(Interface.class, a -> ((Interface)a).value());

    Class c;

    @Getter
    StructureComponentInstanceBuilder<Annotation> handler;

    StructureAnnotationDefinition(Class c, ComponentNameResolver<java.lang.annotation.Annotation> resolver) {
        this.c = c;
        this.handler = new StructureComponentInstanceBuilder<>(resolver, this);
    }

    @Override
    public Class getDefinedClass() {
        return this.c;
    }
}
