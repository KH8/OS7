package com.h8.compiler.core.context.components;

import com.h8.os7.core.annotations.components.structure.Component;
import com.h8.os7.core.annotations.components.structure.Controller;
import com.h8.os7.core.annotations.components.structure.Interface;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Annotation;

@Setter @Getter
public class ClassAnnotationContext {
    private Component componentAnnotation;
    private Controller controllerAnnotation;
    private Interface interfaceAnnotation;

    public ClassAnnotationContext(Class c) {
        componentAnnotation = getAnnotation(c, Component.class);
        controllerAnnotation = getAnnotation(c, Controller.class);
        interfaceAnnotation = getAnnotation(c, Interface.class);
    }

    @SuppressWarnings("unchecked")
    private <T extends Annotation> T getAnnotation(Class c, Class<T> aClass) {
        return (T) c.getAnnotation(aClass);
    }
}
