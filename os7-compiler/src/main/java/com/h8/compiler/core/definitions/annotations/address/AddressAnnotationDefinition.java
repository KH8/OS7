package com.h8.compiler.core.definitions.annotations.address;

import com.h8.os7.core.annotations.components.address.*;

public enum AddressAnnotationDefinition {
    ADDRESS(Address.class);

    Class c;

    AddressAnnotationDefinition(Class c) {
        this.c = c;
    }
}
