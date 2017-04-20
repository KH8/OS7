package com.h8.compiler.core.context;

import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.components.*;
import com.h8.compiler.core.context.config.CompilationConfiguration;
import com.h8.compiler.exception.CompilationFailedException;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class CompilationContext {
    @Setter
    @Getter
    private CompilationConfiguration configuration;
    @Setter @Getter
    private String directory;
    @Getter
    private Map<Class, ClassContext> classes = new HashMap<>();
    @Getter
    private ClassAnnotations classAnnotations = new ClassAnnotations();
    @Getter
    private FieldAnnotations fieldAnnotations = new FieldAnnotations();
    @Getter
    private MethodAnnotations methodAnnotations = new MethodAnnotations();
    @Getter
    private Map<String, InstanceContext> instances = new HashMap<>();

    public void putInstance(InstanceContext i)
            throws CompilationFailedException {
        if (!instances.containsKey(i.getName())) {
            instances.put(i.getName(), i);
        } else {
            String message = StringFormatter.format("Component instance name '{1}' already exists", i.getName());
            throw new CompilationFailedException(message);
        }
    }

    public InstanceContext getInstanceByClassOrName(Class c, String n)
            throws CompilationFailedException {
        return n != null && !n.isEmpty() ? getInstanceByName(n) : getInstanceByClass(c);
    }

    private InstanceContext getInstanceByName(String name)
            throws CompilationFailedException {
        InstanceContext result =  instances.get(name);
        if (result == null) {
            String message = StringFormatter.format("Could not find candidate with name '{1}'", name);
            throw new CompilationFailedException(message);
        }
        return result;
    }

    private InstanceContext getInstanceByClass(Class c)
            throws CompilationFailedException {
        long count = getInstanceByClassStream(c).count();
        if (count > 1) {
            String message = StringFormatter.format("There is more than one candidate of type '{1}'", c);
            throw new CompilationFailedException(message);
        }
        Optional<InstanceContext> result = getInstanceByClassStream(c).findFirst();
        if (!result.isPresent()) {
            String message = StringFormatter.format("Could not find candidate of type '{1}'", c);
            throw new CompilationFailedException(message);
        }
        return result.get();
    }

    private Stream<InstanceContext> getInstanceByClassStream(Class c) {
        return instances.values().stream().filter(i -> i.getC().equals(c));
    }
}
