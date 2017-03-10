package com.h8.compiler.core.context.components;

import com.h8.os7.core.annotations.execution.Runner;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Getter
public class MethodAnnotationContext {
    private Runner runnerAnnotation;

    MethodAnnotationContext(Method m) {
        runnerAnnotation = getAnnotation(m, Runner.class);
    }

    @SuppressWarnings("unchecked")
    private <T extends Annotation> T getAnnotation(Method m, Class<T> aClass) {
        return m.getAnnotation(aClass);
    }
}
