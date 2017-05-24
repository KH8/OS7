package com.h8.compiler.core.context.config;

import com.h8.compiler.common.FileReader;
import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.exception.CompilationFailedException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CompilationConfiguration {
    private static final Logger LOGGER = Logger.get(CompilationConfiguration.class);

    private static final String DEFAULT_PROPERTY_FILE_NAME = "s7generator.properties";

    private String fileName;
    private Properties properties;

    public static CompilationConfiguration getConfiguration(String fileName)
            throws CompilationFailedException {
        fileName = fileName != null ?
                fileName : new FileReader().getResourceFileName(DEFAULT_PROPERTY_FILE_NAME);
        CompilationConfiguration config = new CompilationConfiguration();
        config.fileName = fileName;
        config.properties = getPropertiesFromFile(fileName);
        logProperties(config);
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

    private static void logProperties(CompilationConfiguration config) {
        LOGGER.log("Properties loaded from file: <g{1}/>", config.fileName);
        Object[] sortedKeys = config.properties.keySet().stream().sorted().toArray();
        for (Object key : sortedKeys) {
            LOGGER.log("<p{1}/> : <g{2}/>", key, config.properties.get(key));
        }
        LOGGER.printLineSeparator();
    }

    public String getStringProperty(CompilationProperties property) {
        return this.properties.getProperty(property.getName());
    }

    public Integer getIntegerProperty(CompilationProperties property) {
        String p = getStringProperty(property);
        return Integer.parseInt(p);
    }
}
