package com.h8.compiler.core.processors.dependency;

import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContext;
import com.h8.compiler.core.context.components.FieldContext;
import com.h8.compiler.core.context.components.InstanceContext;
import com.h8.compiler.core.processors.AbstractProcessor;
import com.h8.compiler.core.definitions.annotations.dependency.DependencyAnnotationDefinition;
import com.h8.compiler.exception.CompilationFailedException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

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
        for (FieldContext fCtx : context.getFieldAnnotations().get(annotationClass)) {
            handleField(fCtx);
        }
    }

    private void handleField(FieldContext fCtx)
            throws CompilationFailedException {
        DependencyAnnotationDefinition d = DependencyAnnotationDefinition.getByClass(annotationClass);
        if (d != null) {
            d.getHandler().handle(context, fCtx);
        }
    }
}
