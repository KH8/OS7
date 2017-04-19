package com.h8.compiler.config;

import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.exception.CompilationFailedException;
import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final String DEFAULT_PROPERTY_FILE_NAME = "s7generator.properties";

    private static Configuration instance = null;

    @Getter
    private String version;
    @Getter
    private String dbInstances;
    @Getter
    private String dbComponents;

    private Configuration(String fileName)
            throws CompilationFailedException {
        Properties properties = getPropertiesFromFile(fileName);
        this.version = properties.getProperty(ParameterName.VERSION.getName());
        this.dbInstances = properties.getProperty(ParameterName.DB_INSTANCES_NUMBER.getName());
        this.dbComponents = properties.getProperty(ParameterName.DB_COMPONENTS_NUMBER.getName());
    }

    public static Configuration getConfiguration(String fileName)
            throws CompilationFailedException {
        if (instance == null) {
            fileName = fileName != null ? fileName : DEFAULT_PROPERTY_FILE_NAME;
            instance = new Configuration(fileName);
        }
        return instance;
    }

    private Properties getPropertiesFromFile(String fileName)
            throws CompilationFailedException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            String message = StringFormatter.format("Parameter file {1} could not be loaded", fileName);
            throw new CompilationFailedException(message, e);
        }
        return properties;
    }

    private enum ParameterName {
        VERSION("version"),
        DB_INSTANCES_NUMBER("db.instances"),
        DB_COMPONENTS_NUMBER("db.components");

        @Getter
        private String name;

        ParameterName(String name) {
            this.name = name;
        }
    }
}
