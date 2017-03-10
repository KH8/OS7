package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.FieldContext;
import com.h8.compiler.core.context.components.InstanceContext;
import com.h8.compiler.core.definitions.annotations.components.handlers.AnnotatedFieldInstanceHandler;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Injectable;

import java.lang.reflect.Field;

class InjectableInstanceInjector extends AnnotatedFieldInstanceHandler {
    private static final Logger LOGGER = Logger.get(InjectableInstanceInjector.class);

    @Override
    protected void handleFieldInstance(CompilationContext context, FieldContext fCtx, InstanceContext iCtx)
            throws CompilationFailedException {
        Field f = fCtx.getF();
        Injectable a = fCtx.getACtx().getInjectableAnnotation();
        InstanceContext injected = getInjectedInstance(iCtx, a.value());
        checkInstanceClassCompatibility(injected, f);
        iCtx.getFields().put(f.getName(), injected);
        LOGGER.log("InstanceContext '{1} [{2}]' injected to field '{3}.{4} [{5}]'",
                injected.getName(), injected.getC().getSimpleName(),
                iCtx.getName(), f.getName(), f.getType().getSimpleName());
    }

    private InstanceContext getInjectedInstance(InstanceContext iCtx, String name)
            throws CompilationFailedException {
        InstanceContext injected = iCtx.getInjectedByName(name);
        if (injected == null) {
            String message = StringFormatter.format("Component instance '{1}' could not be found", name);
            throw new CompilationFailedException(message);
        }
        return injected;
    }

    private void checkInstanceClassCompatibility(InstanceContext injected, Field f)
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
