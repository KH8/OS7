package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Inject;

import java.lang.reflect.Field;

class InjectableInstanceProvider implements FieldAnnotationHandler<Inject> {
    private static final Logger LOGGER = Logger.get(InjectableInstanceProvider.class);

    @Override
    public void handle(CompilationContext context, Inject a, Instance i, Field f)
            throws CompilationFailedException {
        String fiName = getFieldInstanceName(i, f);
        Instance fi = context.getInstanceByClassOrName(f.getType(), fiName);
        if (fi != null) {
            injectInstancesWithAnnotatedNames(i, fi, a);
        } else {
            String message = StringFormatter.format("Component instance '{1}' could not be found", fiName);
            throw new CompilationFailedException(message);
        }
    }

    private String getFieldInstanceName(Instance i, Field f) {
        return StringFormatter.format("{1}.{2}", i.getName(), f.getName());
    }

    private void injectInstancesWithAnnotatedNames(Instance i, Instance fi, Inject a) {
        for (String name : a.value()) {
            injectInstanceWithAnnotatedName(i, fi, name);
        }
    }

    private void injectInstanceWithAnnotatedName(Instance i, Instance fi, String name) {
        Instance injected = i.getFieldByName(name);
        fi.getInjected().put(name, injected);
        LOGGER.log("Instance '{1} [{2}]' provided as injectable to field '{4} [{5}]'",
                injected.getName(), injected.getC().getSimpleName(),
                fi.getName(), fi.getC().getSimpleName());
    }
}
