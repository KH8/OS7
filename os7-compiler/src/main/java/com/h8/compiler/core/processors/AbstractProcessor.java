package com.h8.compiler.core.processors;

import com.h8.compiler.core.context.CompilationContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractProcessor implements Processor {
    protected CompilationContext context;
}
