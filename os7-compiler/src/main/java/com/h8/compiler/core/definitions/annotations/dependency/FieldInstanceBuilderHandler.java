package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Instantiate;

import java.lang.reflect.Field;

class FieldInstanceBuilderHandler implements FieldAnnotationHandler<Instantiate> {
    private static final Logger LOGGER = Logger.get(FieldInstanceBuilderHandler.class);

    @Override
    public void handle(CompilationContext context, Instantiate a, Instance parent, Field f)
            throws CompilationFailedException {
        Instance i = getNewInstance(parent, a, f);
        context.addInstance(i);
        LOGGER.log("Found component field '{1} [{2}]' annotated as '{3}' - instantiated",
                i.getName(), f.getType().getSimpleName(), a.annotationType().getSimpleName());
        addFieldInstanceToParent(parent, i);
    }

    private Instance getNewInstance(Instance parent, Instantiate a, Field f) {
        Instance i = new Instance();
        i.setName(getNewInstanceName(parent, f));
        i.setC(f.getType());
        i.setDefinition(DependencyAnnotationDefinition.INSTANTIATE);
        i.getAnnotations().add(a);
        return i;
    }

    private String getNewInstanceName(Instance parent, Field f) {
        return StringFormatter.format("{1}.{2}", parent.getName(), f.getName());
    }

    private void addFieldInstanceToParent(Instance parent, Instance i) {
        parent.getFields().add(i);
        parent.setFieldsInstantiated(true);
    }
}
