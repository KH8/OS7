package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.annotations.components.handlers.FieldAnnotationHandler;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Injectable;

import java.lang.reflect.Field;

public class InjectableInstanceInjector implements FieldAnnotationHandler<Injectable> {
    private static final Logger LOGGER = Logger.get(InjectableInstanceInjector.class);

    @Override
    public void handle(CompilationContext context, Injectable a, Instance i, Field f)
            throws CompilationFailedException {
        Instance injected = getInjectedInstance(i, a.value());
        checkInstanceClassCompatibility(injected, f);
        i.getFields().put(f.getName(), injected);
        LOGGER.log("Instance '{1} [{2}]' injected to field '{3}.{4} [{5}]'",
                injected.getName(), injected.getC().getSimpleName(),
                i.getName(), f.getName(), f.getType().getSimpleName());
    }

    private Instance getInjectedInstance(Instance i, String name)
            throws CompilationFailedException {
        Instance injected = i.getInjectedByName(name);
        if (injected == null) {
            String message = StringFormatter.format("Component instance '{1}' could not be found", name);
            throw new CompilationFailedException(message);
        }
        return injected;
    }

    private void checkInstanceClassCompatibility(Instance injected, Field f)
            throws CompilationFailedException {
        Class usedClass = injected.getC();
        if (usedClass == null || !isClassCompatibleWithSuperClass(usedClass, f.getType())) {
            String message = StringFormatter.format("Component instance '{1}' is not instance of {2}",
                    injected.getName(), f.getType().getSimpleName());
            throw new CompilationFailedException(message);
        }
    }

    private boolean isClassCompatibleWithSuperClass(Class<?> c, Class<?> s) {
        return c == s || s.isAssignableFrom(c);
    }
}
