package com.h8.compiler.core.context.processor;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.Definition;
import com.h8.compiler.core.definitions.annotations.components.structure.StructureAnnotationDefinition;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.annotation.Annotation;
import java.util.HashMap;

public class StructureComponentFinder extends AbstractProcessor {
    public StructureComponentFinder(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        context.setInstances(new HashMap<>());
        for (Class c : context.getClasses()) {
            buildStructureComponent(c);
        }
    }

    private void buildStructureComponent(Class c)
            throws CompilationFailedException {
        for (StructureAnnotationDefinition d : StructureAnnotationDefinition.values()) {
            Instance i = buildStructureComponent(c, d);
            if (i != null) {
                addInstanceToContext(i);
            }
        }
    }

    private Instance buildStructureComponent(Class c, StructureAnnotationDefinition d) {
        Annotation a = getAnnotation(c, d);
        if (a != null) {
            Logger.log(StructureComponentFinder.class, "Found structure component class '{1}' annotated as '{2}'",
                    c.getSimpleName(), a.annotationType());
            return getNewInstance(c, a, d);
        }
        return null;
    }

    private Annotation getAnnotation(Class c, Definition d) {
        return c.getAnnotation(d.getDefinedClass());
    }

    private Instance getNewInstance(Class c, Annotation a, StructureAnnotationDefinition d) {
        Instance i = new Instance();
        i.setName(d.getName(a));
        i.setC(c);
        i.setAnnotation(a);
        i.setDefinition(d);
        return i;
    }

    private void addInstanceToContext(Instance i)
            throws CompilationFailedException {
        String name = i.getName();
        if (!context.getInstances().containsKey(name)) {
            context.getInstances().put(name, i);
        } else {
            String message = StringFormatter.format("Component instance name {1} already exists", name);
            throw new CompilationFailedException(message);
        }
    }
}
