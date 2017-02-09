package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.os7.core.annotations.dependency.Injectable;

import java.lang.reflect.Field;

public class InjectableHandler implements FieldAnnotationHandler<Injectable> {
    @Override
    public void handle(CompilationContext context, Injectable a, Instance i, Field f) {

    }
}
