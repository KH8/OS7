package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.FieldContext;
import com.h8.compiler.core.context.components.InstanceContext;
import com.h8.compiler.core.definitions.annotations.components.handlers.AnnotatedFieldInstanceHandler;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Use;

import java.lang.reflect.Field;

class ComponentInjector extends AnnotatedFieldInstanceHandler {
    private static final Logger LOGGER = Logger.get(ComponentInjector.class);

    @Override
    protected void handleFieldInstance(CompilationContext context, FieldContext fCtx, InstanceContext iCtx)
            throws CompilationFailedException {
        Field f = fCtx.getF();
        Use a = fCtx.getACtx().getUseAnnotation();
        InstanceContext used = context.getInstanceByClassOrName(f.getType(), a.value());
        iCtx.getFields().put(f.getName(), used);
        LOGGER.log("Component '{1} [<g{2}/>]' injected to field '{3}.{4} [<g{5}/>]'",
                used.getName(), used.getC().getSimpleName(),
                iCtx.getName(), f.getName(), f.getType().getSimpleName());
    }
}
