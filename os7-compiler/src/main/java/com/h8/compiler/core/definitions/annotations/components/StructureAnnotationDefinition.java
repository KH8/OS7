package com.h8.compiler.core.definitions.annotations.components;

import com.h8.os7.core.annotations.components.structure.*;

public enum StructureAnnotationDefinition {
    COMPONENT(Component.class),
    CONTROLLER(Controller.class),
    INTERFACE(Interface.class);

    Class c;

    StructureAnnotationDefinition(Class c) {
        this.c = c;
    }
}
