package com.h8.compiler.core.definitions.annotations.components.structure;

import com.h8.compiler.core.definitions.Definition;
import com.h8.os7.core.annotations.components.structure.*;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum StructureAnnotationDefinition implements Definition {
    COMPONENT(Component.class, cCtx -> cCtx.getACtx().getComponentAnnotation().value()),
    CONTROLLER(Controller.class, cCtx -> cCtx.getACtx().getControllerAnnotation().value()),
    INTERFACE(Interface.class, cCtx -> cCtx.getACtx().getInterfaceAnnotation().value());

    Class c;

    @Getter
    ComponentInstanceBuilder handler;

    StructureAnnotationDefinition(Class c, ComponentNameResolver resolver) {
        this.c = c;
        this.handler = new ComponentInstanceBuilder(resolver);
    }

    @Override
    public Class getDefinedClass() {
        return this.c;
    }
}
