package com.h8.compiler.core.definitions.annotations.components.structure;

import java.lang.annotation.Annotation;

interface ComponentNameResolver<T extends Annotation> {
    String getName(T a);
}
