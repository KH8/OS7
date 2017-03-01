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

    protected void iterateThroughClasses(Handler<Class> h)
            throws CompilationFailedException {
        for (ClassContext cCtx : new ArrayList<>(context.getClasses())) {
            h.handle(cCtx.getC());
        }
    }

    private void iterateThroughInstances(Handler<InstanceContext> h)
            throws CompilationFailedException {
        for (InstanceContext i : new ArrayList<>(context.getInstances().values())) {
            h.handle(i);
        }
    }

    protected void iterateThroughAllInstanceClassFields(FieldHandler h)
            throws CompilationFailedException {
        iterateThroughInstances(i -> iterateThroughInstanceClassFields(i, h));
    }

    private void iterateThroughInstanceClassFields(InstanceContext i, FieldHandler h)
            throws CompilationFailedException {
        Class c = i.getC();
        for (Field f : c.getDeclaredFields()) {
            h.handle(i, f);
        }
    }

    protected interface FieldHandler {
        void handle(InstanceContext i, Field f) throws CompilationFailedException;
    }

    protected interface Handler<T> {
        void handle(T t) throws CompilationFailedException;
    }
}
