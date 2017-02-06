package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Use;

import java.lang.reflect.Field;

class FieldInstanceInjector implements FieldAnnotationHandler<Use> {
    private static final Logger LOGGER = Logger.get(FieldInstanceBuilderHandler.class);

    @Override
    public void handle(CompilationContext context, Use a, Instance i, Field f)
            throws CompilationFailedException {
        Instance used = context.getInstanceByClassOrName(f.getType(), a.value());
        i.getFields().add(used);
        LOGGER.log("Instance '{1} [{2}]' annotated as '{3}' - injected as field '{4}.{5} [{6}]'",
                used.getName(), used.getC().getSimpleName(), a.annotationType().getSimpleName(),
                i.getName(), f.getName(), f.getType().getSimpleName());
    }
}
