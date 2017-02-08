package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Inject;

import java.lang.reflect.Field;

class InstanceInjector implements FieldAnnotationHandler<Inject> {
    private static final Logger LOGGER = Logger.get(InstanceInjector.class);

    @Override
    public void handle(CompilationContext context, Inject a, Instance i, Field f)
            throws CompilationFailedException {
        String fieldInstanceName = getFieldInstanceName(i, f);
        Instance fieldInstance = context.getInstanceByClassOrName(f.getType(), fieldInstanceName);
        if (fieldInstance != null) {
            injectInstancesWithAnnotatedNames(i, fieldInstance, a);
        } else {
            String message = StringFormatter.format("Component instance '{1}' could not be found", fieldInstanceName);
            throw new CompilationFailedException(message);
        }
    }

    private String getFieldInstanceName(Instance i, Field f) {
        return StringFormatter.format("{1}.{2}", i.getName(), f.getName());
    }

    private void injectInstancesWithAnnotatedNames(Instance i, Instance fieldInstance, Inject a) {
        for (String name : a.value()) {
            injectInstanceWithAnnotatedName(i, fieldInstance, name, a);
        }
    }

    private void injectInstanceWithAnnotatedName(Instance i, Instance fieldInstance, String name, Inject a) {
        Instance injectedInstance = i.getFieldByName(name);
        fieldInstance.getInjected().add(injectedInstance);
        LOGGER.log("Field '{1} [{2}]' annotated as '{3}' - enriched with injectable instance '{4} [{5}]'",
                fieldInstance.getName(), fieldInstance.getC().getSimpleName(), a.annotationType().getSimpleName(),
                injectedInstance.getName(), injectedInstance.getC().getSimpleName());
    }
}
