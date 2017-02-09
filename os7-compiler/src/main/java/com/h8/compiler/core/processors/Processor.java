package com.h8.compiler.core.processors;

import com.h8.compiler.exception.CompilationFailedException;

interface Processor {
    void process() throws CompilationFailedException;
}
