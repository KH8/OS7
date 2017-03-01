package com.h8.compiler.core.context.components;

import com.h8.os7.core.annotations.dependency.Inject;
import com.h8.os7.core.annotations.dependency.Injectable;
import com.h8.os7.core.annotations.dependency.Instantiate;
import com.h8.os7.core.annotations.dependency.Use;

public class FieldAnnotationContext {
    private Inject injectAnnotation;
    private Injectable injectableAnnotation;
    private Instantiate instantiateAnnotation;
    private Use useAnnotation;
}
