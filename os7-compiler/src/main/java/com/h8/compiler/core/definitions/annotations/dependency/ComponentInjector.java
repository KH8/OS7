package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Use;

import java.lang.reflect.Field;

public class ComponentInjector implements FieldAnnotationHandler<Use> {
    private static final Logger LOGGER = Logger.get(FieldInstanceBuilder.class);

    @Override
    public void handle(CompilationContext context, Use a, Instance i, Field f)
            throws CompilationFailedException {
        Instance used = context.getInstanceByClassOrName(f.getType(), a.value());
        i.getFields().put(f.getName(), used);
        LOGGER.log("Component '{1} [{2}]' injected to field '{3} [{4}]'",
                used.getName(), used.getC().getSimpleName(), f.getName(), f.getType().getSimpleName());
    }
}
