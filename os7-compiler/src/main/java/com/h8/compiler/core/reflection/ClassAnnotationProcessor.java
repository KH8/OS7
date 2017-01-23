package com.h8.compiler.core.reflection;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.CompilationContext;
import com.h8.compiler.core.definitions.Definition;
import com.h8.compiler.core.definitions.annotations.components.structure.StructureAnnotationDefinition;

import java.lang.annotation.Annotation;

public class ClassAnnotationProcessor {
    public void printClassAnnotations(CompilationContext ctx) {
        ctx.getClassList().forEach(this::printClassAnnotation);
    }

    private void printClassAnnotation(Class objectClass) {
        for (Definition d : StructureAnnotationDefinition.values()) {
            printClassAnnotation(objectClass, d.getDefinedClass());
        }
    }

    private void printClassAnnotation(Class objectClass, Class annotationClass) {
        Annotation annotation = objectClass.getAnnotation(annotationClass);
        if (annotation != null) {
            Logger.log(ClassAnnotationProcessor.class, "Class '{1}' is annotated as '{2}'",
                    objectClass.getSimpleName(), annotation.annotationType());
        }
    }
}
