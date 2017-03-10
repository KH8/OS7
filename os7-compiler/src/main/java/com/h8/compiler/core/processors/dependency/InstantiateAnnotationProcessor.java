package com.h8.compiler.core.processors.dependency;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.os7.core.annotations.dependency.Instantiate;

public class InstantiateAnnotationProcessor extends AbstractDependencyAnnotationProcessor<Instantiate> {
    public InstantiateAnnotationProcessor(CompilationContext context) {
        super(context, Instantiate.class);
    }
}
