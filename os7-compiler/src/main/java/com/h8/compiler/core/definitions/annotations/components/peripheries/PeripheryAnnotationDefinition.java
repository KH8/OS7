package com.h8.compiler.core.definitions.annotations.components.peripheries;

import com.h8.compiler.core.definitions.Definition;
import com.h8.os7.core.annotations.components.periphery.*;

public enum PeripheryAnnotationDefinition implements Definition {
    INPUT(Input.class),
    OUTPUT(Output.class);

    Class c;

    PeripheryAnnotationDefinition(Class c) {
        this.c = c;
    }

    @Override
    public Class getDefinedClass() {
        return this.c;
    }
}
