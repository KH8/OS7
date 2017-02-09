package com.h8.compiler.core.context.processor.dependency;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.os7.core.annotations.dependency.Inject;

public class InjectAnnotationProcessor extends AbstractDependencyAnnotationProcessor<Inject> {
    public InjectAnnotationProcessor(CompilationContext context) {
        super(context, Inject.class);
    }
}
