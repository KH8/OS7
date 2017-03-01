package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.Instance;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Instantiate;

import java.lang.reflect.Field;

public class FieldInstanceBuilder implements FieldAnnotationHandler<Instantiate> {
    private static final Logger LOGGER = Logger.get(FieldInstanceBuilder.class);

    @Override
    public void handle(CompilationContext context, Instantiate a, Instance i, Field f)
            throws CompilationFailedException {
        String name = getNewInstanceName(i, f);
        Instance fi = new Instance(name, f.getType());
        context.putInstance(name, fi);
        LOGGER.log("New instance for field '{1} [{2}] created'", name, f.getType().getSimpleName());
        addFieldInstanceToParent(i, fi);
    }

    private String getNewInstanceName(Instance i, Field f) {
        return StringFormatter.format("{1}.{2}", i.getName(), f.getName());
    }

    private void addFieldInstanceToParent(Instance i, Instance fi) {
        i.getFields().put(fi.getName(), fi);
        i.setFieldsInstantiated(true);
    }
}
