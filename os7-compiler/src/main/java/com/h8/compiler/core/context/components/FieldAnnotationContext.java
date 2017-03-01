package com.h8.compiler.core.context.components;

import com.h8.os7.core.annotations.dependency.Inject;
import com.h8.os7.core.annotations.dependency.Injectable;
import com.h8.os7.core.annotations.dependency.Instantiate;
import com.h8.os7.core.annotations.dependency.Use;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Getter
public class FieldAnnotationContext {
    private Inject injectAnnotation;
    private Injectable injectableAnnotation;
    private Instantiate instantiateAnnotation;
    private Use useAnnotation;

    public FieldAnnotationContext(Field f) {
        injectAnnotation = getAnnotation(f, Inject.class);
        injectableAnnotation = getAnnotation(f, Injectable.class);
        instantiateAnnotation = getAnnotation(f, Instantiate.class);
        useAnnotation = getAnnotation(f, Use.class);
    }

    @SuppressWarnings("unchecked")
    private <T extends Annotation> T getAnnotation(Field f, Class<T> aClass) {
        return f.getAnnotation(aClass);
    }
}
