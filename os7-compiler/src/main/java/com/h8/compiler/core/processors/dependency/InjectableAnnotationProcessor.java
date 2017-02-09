package com.h8.compiler.core.processors.dependency;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.os7.core.annotations.dependency.Injectable;

public class InjectableAnnotationProcessor extends AbstractDependencyAnnotationProcessor<Injectable> {
    public InjectableAnnotationProcessor(CompilationContext context) {
        super(context, Injectable.class);
    }
}
