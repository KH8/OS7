package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.reflect.Field;

interface DependencyHandler {
    void handle(CompilationContext context, Instance i, Field f) throws CompilationFailedException;
}
