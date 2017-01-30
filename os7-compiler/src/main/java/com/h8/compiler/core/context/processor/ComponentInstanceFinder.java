package com.h8.compiler.core.context.processor;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.ComponentInstance;
import com.h8.compiler.core.definitions.Definition;
import com.h8.compiler.core.definitions.annotations.components.structure.StructureAnnotationDefinition;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.UUID;

public class ComponentInstanceFinder extends AbstractProcessor {
    public ComponentInstanceFinder(CompilationContext context) {
        super(context);
    }

    @Override
    public void process() {
        context.setInstances(new HashMap<>());
        context.getClassList().forEach(this::buildComponentInstance);
    }

    private void buildComponentInstance(Class objectClass) {
        Definition definition = findDefinition(objectClass);
        String id = UUID.randomUUID().toString();
        ComponentInstance instance = new ComponentInstance(id, objectClass, definition);
        context.getInstances().put(id, instance);
    }

    private Definition findDefinition(Class objectClass) {
        for (Definition d : StructureAnnotationDefinition.values()) {
            if (matchDefinition(objectClass, d)) {
                return d;
            }
        }
        return null;
    }

    private boolean matchDefinition(Class objectClass, Definition definition) {
        Annotation annotation = objectClass.getAnnotation(definition.getDefinedClass());
        if (annotation != null) {
            Logger.log(ComponentInstanceFinder.class, "Class '{1}' is annotated as '{2}'",
                    objectClass.getSimpleName(), annotation.annotationType());
            return true;
        }
        return false;
    }
}
