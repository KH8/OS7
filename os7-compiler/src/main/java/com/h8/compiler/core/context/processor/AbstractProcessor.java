package com.h8.compiler.core.context.processor;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.exception.CompilationFailedException;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;

@AllArgsConstructor
abstract class AbstractProcessor implements Processor {
    protected CompilationContext context;

    void iterateThroughClasses(Handler<Class> h)
            throws CompilationFailedException {
        for (Class c : new ArrayList<>(context.getClasses())) {
            h.handle(c);
        }
    }

    void iterateThroughInstances(Handler<Instance> h)
            throws CompilationFailedException {
        for (Instance i : new ArrayList<>(context.getInstances())) {
            h.handle(i);
        }
    }

    void iterateThroughAllInstanceClassFields(FieldHandler h)
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

    interface FieldHandler {
        void handle(Instance i, Field f) throws CompilationFailedException;
    }

    interface Handler<T> {
        void handle(T t) throws CompilationFailedException;
    }
}
