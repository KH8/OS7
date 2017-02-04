package com.h8.compiler.core.context.processor;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.annotations.dependency.DependencyAnnotationDefinition;
import com.h8.compiler.exception.CompilationFailedException;
import com.h8.os7.core.annotations.dependency.Instantiate;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ComponentDependenciesProcessor extends AbstractProcessor {
    private static final Logger LOGGER = Logger.get(ComponentDependenciesProcessor.class);

    public ComponentDependenciesProcessor(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        instantiateFields();
        handleFields();
    }

    private void instantiateFields()
            throws CompilationFailedException {
        iterateThroughAllComponentFields((i, f)  -> {
            if (f.getAnnotation(Instantiate.class) != null) {
                LOGGER.log("Found field '{1}.{2} [{3}]' to be instantiated",
                        i.getName(), f.getName(), f.getType().getSimpleName());
                DependencyAnnotationDefinition.INSTANTIATE.handle(context, i, f);
            }
        });
    }

    private void handleFields()
            throws CompilationFailedException {
        iterateThroughAllComponentFields((i, f) -> {
            for (DependencyAnnotationDefinition d : DependencyAnnotationDefinition.values()) {
                if (!DependencyAnnotationDefinition.INSTANTIATE.equals(d) && f.getAnnotation(d.getDefinedClass()) != null) {
                    LOGGER.log("Found field '{1}.{2} [{3}]' annotated with '{4}'",
                            i.getName(), f.getName(), f.getType().getSimpleName(), d.getDefinedClass().getSimpleName());
                    d.handle(context, i, f);
                }
            }
        });
    }

    private void iterateThroughAllComponentFields(FieldHandler fh)
            throws CompilationFailedException {
        for (Instance i : new ArrayList<>(context.getInstances())) {
            Class c = i.getC();
            for (Field f : c.getDeclaredFields()) {
                fh.handle(i, f);
            }
        }
    }

    private interface FieldHandler {
        void handle(Instance i, Field f)
                throws CompilationFailedException;
    }
}
