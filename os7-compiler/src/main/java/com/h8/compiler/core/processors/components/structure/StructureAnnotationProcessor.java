package com.h8.compiler.core.processors.components.structure;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContext;
import com.h8.compiler.core.processors.AbstractProcessor;
import com.h8.compiler.core.definitions.annotations.components.structure.StructureAnnotationDefinition;
import com.h8.compiler.exception.CompilationFailedException;

import java.util.List;

public class StructureAnnotationProcessor extends AbstractProcessor {
    public StructureAnnotationProcessor(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        handleStructureAnnotationDefinitions();
    }

    private void handleStructureAnnotationDefinitions()
            throws CompilationFailedException {
        for (StructureAnnotationDefinition d : StructureAnnotationDefinition.values()) {
            handleStructureAnnotationDefinition(d);
        }
    }

    private void handleStructureAnnotationDefinition(StructureAnnotationDefinition d)
            throws CompilationFailedException {
        List<ClassContext> classes = context.getClassAnnotations().get(d.getDefinedClass());
        if (classes != null) {
            handleAnnotatedClassContexts(classes, d);
        }
    }

    private void handleAnnotatedClassContexts(List<ClassContext> classes, StructureAnnotationDefinition d)
            throws CompilationFailedException {
        for (ClassContext cCtx : classes) {
            handleAnnotatedClassContext(cCtx, d);
        }
    }

    private void handleAnnotatedClassContext(ClassContext cCtx, StructureAnnotationDefinition d)
            throws CompilationFailedException {
        d.getHandler().handle(context, cCtx);
    }
}
