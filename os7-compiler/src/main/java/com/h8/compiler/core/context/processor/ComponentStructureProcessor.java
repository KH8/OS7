package com.h8.compiler.core.context.processor;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.Definition;
import com.h8.compiler.core.definitions.annotations.components.structure.StructureAnnotationDefinition;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class ComponentStructureProcessor extends AbstractProcessor {
    private static final Logger LOGGER = Logger.get(ComponentStructureProcessor.class);

    public ComponentStructureProcessor(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        initializeInstanceMap();
        for (Class c : context.getClasses()) {
            buildStructureComponent(c);
        }
    }

    private void initializeInstanceMap() {
        if (context.getInstances() == null) {
            context.setInstances(new ArrayList<>());
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
            LOGGER.log("Found structure component class '{1} [{2}]' annotated as '{3}'",
                    d.getName(a), c.getSimpleName(), a.annotationType().getSimpleName());
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
        if (!context.getInstances().contains(i)) {
            context.getInstances().add(i);
        } else {
            String message = StringFormatter.format("Component instance name '{1}' already exists", i.getName());
            throw new CompilationFailedException(message);
        }
    }
}
