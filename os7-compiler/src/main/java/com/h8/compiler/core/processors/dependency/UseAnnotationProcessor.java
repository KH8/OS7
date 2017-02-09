package com.h8.compiler.core.processors.dependency;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.os7.core.annotations.dependency.Use;

public class UseAnnotationProcessor extends AbstractDependencyAnnotationProcessor<Use> {
    public UseAnnotationProcessor(CompilationContext context) {
        super(context, Use.class);
    }
}
