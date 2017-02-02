package com.h8.compiler.core.context.processor;

import com.h8.compiler.exception.CompilationFailedException;

interface Processor {
    void process() throws CompilationFailedException;
}
