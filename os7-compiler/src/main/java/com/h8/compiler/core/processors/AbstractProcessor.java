package com.h8.compiler.core.processors;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.Instance;
import com.h8.compiler.exception.CompilationFailedException;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;

@AllArgsConstructor
public abstract class AbstractProcessor implements Processor {
    protected CompilationContext context;

    protected void iterateThroughClasses(Handler<Class> h)
            throws CompilationFailedException {
        for (Class c : new ArrayList<>(context.getClasses())) {
            h.handle(c);
        }
    }

    private void iterateThroughInstances(Handler<Instance> h)
            throws CompilationFailedException {
        for (Instance i : new ArrayList<>(context.getInstances().values())) {
            h.handle(i);
        }
    }

    protected void iterateThroughAllInstanceClassFields(FieldHandler h)
            throws CompilationFailedException {
        iterateThroughInstances(i -> iterateThroughInstanceClassFields(i, h));
    }

    private void iterateThroughInstanceClassFields(Instance i, FieldHandler h)
            throws CompilationFailedException {
        Class c = i.getC();
        for (Field f : c.getDeclaredFields()) {
            h.handle(i, f);
        }
    }

    protected interface FieldHandler {
        void handle(Instance i, Field f) throws CompilationFailedException;
    }

    protected interface Handler<T> {
        void handle(T t) throws CompilationFailedException;
    }
}
