package com.h8.compiler.core.definitions.annotations.components.structure;

import com.h8.compiler.core.context.components.ClassContext;

interface ComponentNameResolver {
    String getName(ClassContext cCtx);
}
