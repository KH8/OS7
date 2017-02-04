package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

class UseHandler implements DependencyHandler {
    @Override
    public void handle(CompilationContext context, Instance i, Field f)
            throws CompilationFailedException {
        Instance used = findInstanceByClass(context, f.getType());
        i.getFields().add(used);
    }

    private Instance findInstanceByClass(CompilationContext context, Class c)
            throws CompilationFailedException {
        Supplier<Stream<Instance>> s = () -> context.getInstances()
                .stream()
                .filter(instance -> c.equals(instance.getC()));
        validateForTooManyInstances(s.get(), c);
        return getInstanceIfExists(s.get(), c);
    }

    private void validateForTooManyInstances(Stream<Instance> s, Class c)
            throws CompilationFailedException {
        if (s.count() > 1) {
            String message = StringFormatter.format("There is more than one candidate of type '{1}'", c);
            throw new CompilationFailedException(message);
        }
    }

    private Instance getInstanceIfExists(Stream<Instance> s, Class c)
            throws CompilationFailedException {
        Optional<Instance> result = s.findFirst();
        if (!result.isPresent()) {
            String message = StringFormatter.format("Could not find candidate of type '{1}'", c);
            throw new CompilationFailedException(message);
        }
        return result.get();
    }
}
