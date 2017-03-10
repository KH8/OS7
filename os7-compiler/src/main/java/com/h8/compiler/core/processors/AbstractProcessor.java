package com.h8.compiler.core.processors;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContext;
import com.h8.compiler.core.context.components.InstanceContext;
import com.h8.compiler.exception.CompilationFailedException;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;

@AllArgsConstructor
public abstract class AbstractProcessor implements Processor {
    protected CompilationContext context;
}
