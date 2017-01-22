package com.h8.compiler.core.definitions.annotations.peripheries;

import com.h8.os7.core.annotations.components.periphery.*;

public enum PeripheryAnnotationDefinition {
    INPUT(Input.class),
    OUTPUT(Output.class);

    Class c;

    PeripheryAnnotationDefinition(Class c) {
        this.c = c;
    }
}
