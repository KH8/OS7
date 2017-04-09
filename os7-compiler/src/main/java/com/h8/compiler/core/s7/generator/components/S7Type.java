package com.h8.compiler.core.s7.generator.components;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class S7Type extends S7CodeComponent {
    private String name;
    private String version;
    private Set<S7Parameter> parameters;
}
