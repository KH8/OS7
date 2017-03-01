package com.h8.compiler.core.definitions.annotations.components.structure;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.InstanceContext;
import com.h8.compiler.core.definitions.annotations.components.handlers.ClassAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.annotation.Annotation;

public class ComponentInstanceBuilder<T extends Annotation> implements ClassAnnotationHandler<T> {
    private static final Logger LOGGER = Logger.get(ComponentInstanceBuilder.class);

    private ComponentNameResolver<T> resolver;

    ComponentInstanceBuilder(ComponentNameResolver<T> resolver) {
        this.resolver = resolver;
    }

    @Override
    public void handle(CompilationContext context, T a, Class c)
            throws CompilationFailedException {
        String name = this.resolver.getName(a);
        context.putInstance(name, new InstanceContext(name, c));
        LOGGER.log("New instance for component '{1} [{2}]' created", name, c.getSimpleName());
    }
}
