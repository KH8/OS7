package com.h8.compiler.core.context.processor;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;
import com.h8.compiler.core.definitions.annotations.dependency.DependencyAnnotationDefinition;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AbstractDependencyAnnotationProcessor<T extends Annotation> extends AbstractProcessor {
    public AbstractDependencyAnnotationProcessor(CompilationContext context, Class<T> c) {
        super(context);
        this.annotationClass = c;
    }

    private Class<T> annotationClass;

    @Override
    public void process()
            throws CompilationFailedException {
        handleFields();
    }

    private void handleFields()
            throws CompilationFailedException {
        iterateThroughAllInstanceClassFields((i, f)  -> {
            handleField(i, f);
        });
    }

    protected void handleField(Instance i, Field f)
            throws CompilationFailedException {
        T a = f.getAnnotation(annotationClass);
        DependencyAnnotationDefinition d = DependencyAnnotationDefinition.getByClass(annotationClass);
        if (a != null && d != null) {
            d.getHandler().handle(context, a, i, f);
        }
    }
}
