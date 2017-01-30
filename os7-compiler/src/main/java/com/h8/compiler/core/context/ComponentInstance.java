package com.h8.compiler.core.context;

import com.h8.compiler.core.definitions.Definition;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class ComponentInstance {
    private String name;
    private Class c;
    private Definition definition;
}
