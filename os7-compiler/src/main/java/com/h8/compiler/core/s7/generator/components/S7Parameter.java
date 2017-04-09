package com.h8.compiler.core.s7.generator.components;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class S7Parameter extends S7CodeComponent {
    private String name;
    private S7Type type;
    private String comment;
}
