package com.h8.compiler.core.context.processor;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.definitions.Definition;
import com.h8.compiler.core.definitions.annotations.components.structure.StructureAnnotationDefinition;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.annotation.Annotation;

public class StructureAnnotationProcessor extends AbstractProcessor {
    public StructureAnnotationProcessor(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        iterateThroughClasses(this::handleStructureAnnotation);
    }

    private void handleStructureAnnotation(Class c)
            throws CompilationFailedException {
        for (StructureAnnotationDefinition d : StructureAnnotationDefinition.values()) {
            handleStructureAnnotation(c, d);
        }
    }

    private void handleStructureAnnotation(Class c, StructureAnnotationDefinition d)
            throws CompilationFailedException {
        Annotation a = getAnnotation(c, d);
        if (a != null) {
            d.getHandler().handle(context, a, c);
        }
    }

    private Annotation getAnnotation(Class c, Definition d) {
        return c.getAnnotation(d.getDefinedClass());
    }
}
