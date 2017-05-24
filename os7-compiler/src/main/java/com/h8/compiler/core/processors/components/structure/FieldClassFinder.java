package com.h8.compiler.core.processors.components.structure;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContext;
import com.h8.compiler.core.context.components.FieldContext;
import com.h8.compiler.core.context.components.builders.ClassContextBuilder;
import com.h8.compiler.core.processors.AbstractProcessor;
import com.h8.compiler.exception.CompilationFailedException;
import org.apache.commons.lang3.ClassUtils;

import java.util.HashSet;
import java.util.Set;

public class FieldClassFinder extends AbstractProcessor {
    private static final Logger LOGGER = Logger.get(FieldClassFinder.class);
    private boolean newClassContextsCreated = true;
    private Set<Class<?>> newClasses = new HashSet<>();

    public FieldClassFinder(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        while (newClassContextsCreated) {
            processAllClasses();
        }
    }

    private void processAllClasses()
            throws CompilationFailedException {
        newClassContextsCreated = false;
        newClasses = new HashSet<>();
        for (ClassContext cCtx : context.getClasses().values()) {
            processAllClassFields(cCtx);
        }
        processAllNewClasses();
    }

    private void processAllClassFields(ClassContext cCtx)
            throws CompilationFailedException {
        for (FieldContext fCtx : cCtx.getFields().values()) {
            processClassInstanceField(fCtx);
        }
    }

    private void processClassInstanceField(FieldContext fCtx)
            throws CompilationFailedException {
        Class c = fCtx.getF().getType();
        if (checkIfClassShouldBeRegistered(c)) {
            newClasses.add(c);
            newClassContextsCreated = true;
        }
    }

    private boolean checkIfClassShouldBeRegistered(Class c) {
        return !context.getClasses().containsKey(c)
                && !c.isEnum()
                && !c.isArray()
                && !ClassUtils.isPrimitiveOrWrapper(c);
    }

    private void processAllNewClasses()
            throws CompilationFailedException {
        newClasses.forEach(this::registerClassContext);
    }

    private void registerClassContext(Class c) {
        ClassContextBuilder.build(context, c);
        LOGGER.log("New class context for '{1}' created'", c.getSimpleName());
    }
}
