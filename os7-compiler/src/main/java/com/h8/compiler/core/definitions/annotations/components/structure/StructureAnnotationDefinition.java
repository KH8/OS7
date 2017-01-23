package com.h8.compiler.core.definitions.annotations.components.structure;

import com.h8.compiler.core.definitions.Definition;
import com.h8.os7.core.annotations.components.structure.*;

public enum StructureAnnotationDefinition implements Definition {
    COMPONENT(Component.class),
    CONTROLLER(Controller.class),
    INTERFACE(Interface.class);

    Class c;

    StructureAnnotationDefinition(Class c) {
        this.c = c;
    }

    @Override
    public Class getDefinedClass() {
        return this.c;
    }
}
