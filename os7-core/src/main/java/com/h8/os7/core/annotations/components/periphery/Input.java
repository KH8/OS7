package com.h8.os7.core.annotations.components.periphery;

import com.h8.os7.core.annotations.components.address.Address;
import com.h8.os7.core.types.InputType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Input {
    InputType type();
    Address address();
}
