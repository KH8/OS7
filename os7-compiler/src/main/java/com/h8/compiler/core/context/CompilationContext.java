package com.h8.compiler.core.context;

import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.components.Instance;
import com.h8.compiler.exception.CompilationFailedException;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Stream;

public class CompilationContext {
    @Setter @Getter
    private String directory;
    @Setter @Getter
    private List<Class> classes;
    @Getter
    private Map<String, Instance> instances = new HashMap<>();

    public void putInstance(String name, Instance i)
            throws CompilationFailedException {
        if (!instances.containsKey(name)) {
            instances.put(name, i);
        } else {
            String message = StringFormatter.format("Component instance name '{1}' already exists", name);
            throw new CompilationFailedException(message);
        }
    }

    public Instance getInstanceByClassOrName(Class c, String n)
            throws CompilationFailedException {
        return n != null && !n.isEmpty() ? getInstanceByName(n) : getInstanceByClass(c);
    }

    private Instance getInstanceByName(String name)
            throws CompilationFailedException {
        Instance result =  instances.get(name);
        if (result == null) {
            String message = StringFormatter.format("Could not find candidate with name '{1}'", name);
            throw new CompilationFailedException(message);
        }
        return result;
    }

    private Instance getInstanceByClass(Class c)
            throws CompilationFailedException {
        long count = getInstanceByClassStream(c).count();
        if (count > 1) {
            String message = StringFormatter.format("There is more than one candidate of type '{1}'", c);
            throw new CompilationFailedException(message);
        }
        Optional<Instance> result = getInstanceByClassStream(c).findFirst();
        if (!result.isPresent()) {
            String message = StringFormatter.format("Could not find candidate of type '{1}'", c);
            throw new CompilationFailedException(message);
        }
        return result.get();
    }

    private Stream<Instance> getInstanceByClassStream(Class c) {
        return instances.values().stream().filter(i -> i.getC().equals(c));
    }
}
