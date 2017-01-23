package com.h8.compiler.core.definitions.annotations.components.address;

import com.h8.compiler.core.definitions.Definition;
import com.h8.os7.core.annotations.components.address.*;

public enum AddressAnnotationDefinition implements Definition {
    ADDRESS(Address.class);

    Class c;

    AddressAnnotationDefinition(Class c) {
        this.c = c;
    }

    @Override
    public Class getDefinedClass() {
        return this.c;
    }
}
