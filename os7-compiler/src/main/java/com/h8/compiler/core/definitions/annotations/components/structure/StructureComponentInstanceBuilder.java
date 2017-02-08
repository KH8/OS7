package com.h8.compiler.core.definitions.annotations.components.structure;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.Definition;
import com.h8.compiler.core.definitions.annotations.components.handlers.ClassAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.annotation.Annotation;

public class StructureComponentInstanceBuilder<T extends Annotation> implements ClassAnnotationHandler<T> {
    private static final Logger LOGGER = Logger.get(StructureComponentInstanceBuilder.class);

    private ComponentNameResolver<T> resolver;
    private Definition definition;

    StructureComponentInstanceBuilder(ComponentNameResolver<T> resolver, Definition definition) {
        this.resolver = resolver;
        this.definition = definition;
    }

    @Override
    public void handle(CompilationContext context, T a, Class c)
            throws CompilationFailedException {
        Instance i = getNewInstance(a, c);
        context.addInstance(i);
        LOGGER.log("Found component '{1} [{2}]' annotated as '{3}' - instantiated",
                i.getName(), c.getSimpleName(), a.annotationType().getSimpleName());
    }

    private Instance getNewInstance(T a, Class c) {
        Instance i = new Instance();
        i.setName(this.resolver.getName(a));
        i.setC(c);
        i.getAnnotations().add(a);
        i.setDefinition(this.definition);
        return i;
    }
}
