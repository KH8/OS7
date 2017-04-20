package com.h8.compiler.core.context.config;

import lombok.Getter;

public enum CompilationProperties {
    VERSION("version"),
    DB_INSTANCES_NUMBER("db.instances"),
    DB_COMPONENTS_NUMBER("db.components");

    @Getter
    private String name;

    CompilationProperties(String name) {
        this.name = name;
    }
}
