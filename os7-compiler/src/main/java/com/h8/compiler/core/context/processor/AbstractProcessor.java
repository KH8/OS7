package com.h8.compiler.core.context.processor;

import com.h8.compiler.core.context.CompilationContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
abstract class AbstractProcessor implements Processor {
    protected CompilationContext context;
}
