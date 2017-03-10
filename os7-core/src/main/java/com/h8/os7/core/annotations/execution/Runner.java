package com.h8.os7.core.annotations.execution;

import com.h8.os7.core.types.RunnerType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Runner {
    RunnerType value();
}
