package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.os7.core.annotations.dependency.Inject;

import java.lang.reflect.Field;

class InjectHandler implements FieldAnnotationHandler<Inject> {
    @Override
    public void handle(CompilationContext context, Inject a, Instance i, Field f) {

    }
}
