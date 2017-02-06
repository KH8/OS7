package com.h8.compiler.core.context;

import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.exception.CompilationFailedException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Setter
@Getter
public class CompilationContext {
    private String directory;
    private List<Class> classes;
    private List<Instance> instances = new ArrayList<>();

    public void addInstance(Instance i)
            throws CompilationFailedException {
        if (!instances.contains(i)) {
            instances.add(i);
        } else {
            String message = StringFormatter.format("Component instance name '{1}' already exists", i.getName());
            throw new CompilationFailedException(message);
        }
    }

    public Instance getInstanceByClassOrName(Class c, String n)
            throws CompilationFailedException {
        long count = getInstanceByClassOrNameStream(c, n).count();
        if (count > 1) {
            String message = StringFormatter.format("There is more than one candidate of type '{1}'", c);
            throw new CompilationFailedException(message);
        }
        Optional<Instance> result = getInstanceByClassOrNameStream(c, n).findFirst();
        if (!result.isPresent()) {
            String message = StringFormatter.format("Could not find candidate of type '{1}'", c);
            throw new CompilationFailedException(message);
        }
        return result.get();
    }

    public Stream<Instance> getInstanceByClassOrNameStream(Class c, String n) {
        return instances.stream().filter(i -> {
            boolean result = i.getC().equals(c);
            if (n != null && !n.isEmpty()) {
                result &= i.getName().equals(n);
            }
            return result;
        });
    }
}
