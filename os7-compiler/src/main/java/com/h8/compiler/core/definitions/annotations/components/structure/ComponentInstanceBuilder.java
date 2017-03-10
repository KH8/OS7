package com.h8.compiler.core.definitions.annotations.components.structure;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContext;
import com.h8.compiler.core.context.components.InstanceContext;
import com.h8.compiler.core.definitions.annotations.components.handlers.ClassAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;

public class ComponentInstanceBuilder implements ClassAnnotationHandler {
    private static final Logger LOGGER = Logger.get(ComponentInstanceBuilder.class);

    private ComponentNameResolver resolver;

    ComponentInstanceBuilder(ComponentNameResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void handle(CompilationContext context, ClassContext cCtx)
            throws CompilationFailedException {
        String name = this.resolver.getName(cCtx);
        InstanceContext iCtx = new InstanceContext(name, cCtx);
        context.putInstance(iCtx);
        LOGGER.log("New instance for component '{1} [{2}]' created", name, cCtx.getC().getSimpleName());
    }
}
