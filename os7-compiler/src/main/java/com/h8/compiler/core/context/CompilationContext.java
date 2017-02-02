package com.h8.compiler.core.context;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class CompilationContext {
    private String directory;
    private List<Class> classes;
    private Map<String, Instance> instances;
}
