package com.h8.compiler.core.context.config;

import com.h8.compiler.common.FileReader;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.exception.CompilationFailedException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CompilationConfiguration {
    private static final String DEFAULT_PROPERTY_FILE_NAME = "s7generator.properties";

    private Properties properties;

    public static CompilationConfiguration getConfiguration(String fileName)
            throws CompilationFailedException {
        fileName = fileName != null ?
                fileName : new FileReader().getResourceFileName(DEFAULT_PROPERTY_FILE_NAME);
        CompilationConfiguration config = new CompilationConfiguration();
        config.properties = getPropertiesFromFile(fileName);
        return config;
    }

    private static Properties getPropertiesFromFile(String fileName)
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

    public String getStringProperty(CompilationProperties property) {
        return this.properties.getProperty(property.getName());
    }

    public Integer getIntegerProperty(CompilationProperties property) {
        String p = getStringProperty(property);
        return Integer.parseInt(p);
    }
}
