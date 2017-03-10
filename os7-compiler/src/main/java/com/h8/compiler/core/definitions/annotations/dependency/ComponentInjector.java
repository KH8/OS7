package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.FieldContext;
import com.h8.compiler.core.context.components.InstanceContext;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Use;

import java.lang.reflect.Field;

public class ComponentInjector implements FieldAnnotationHandler {
    private static final Logger LOGGER = Logger.get(ComponentInjector.class);

    @Override
    public void handle(CompilationContext context, FieldContext fCtx)
            throws CompilationFailedException {
        for (InstanceContext i : fCtx.getCCtx().getInstances().values()) {
            Field f = fCtx.getF();
            Use a = fCtx.getACtx().getUseAnnotation();

            InstanceContext used = context.getInstanceByClassOrName(f.getType(), a.value());
            i.getFields().put(f.getName(), used);
            LOGGER.log("Component '{1} [{2}]' injected to field '{3}.{4} [{5}]'",
                    used.getName(), used.getC().getSimpleName(),
                    i.getName(), f.getName(), f.getType().getSimpleName());
        }
    }
}
