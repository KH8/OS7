package com.h8.compiler.core.processors;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.builders.ClassContextBuilder;
import com.h8.compiler.exception.CompilationFailedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import static java.net.URLClassLoader.newInstance;

public class ClassFileProcessor extends AbstractProcessor {
    private static final Logger LOGGER = Logger.get(ClassFileProcessor.class);

    private static final String CLASS_EXTENSION = ".class";
    private static final String TARGET_CLASSES_LOCATION = "/target/classes/";

    private File directory;
    private URLClassLoader loader;

    public ClassFileProcessor(CompilationContext context)
            throws CompilationFailedException {
        super(context);
        try {
            initialize();
        } catch (FileNotFoundException | MalformedURLException e) {
            throw new CompilationFailedException("Class processor could not be initialized", e);
        }
    }

    private void initialize()
            throws FileNotFoundException, MalformedURLException {
        directory = new File(context.getDirectory() + TARGET_CLASSES_LOCATION);
        if (directory.exists() && directory.isDirectory()) {
            initializeLoader();
        } else {
            throw new FileNotFoundException(
                    StringFormatter.format("Directory '{1}' does not exist.", directory.getAbsolutePath()));
        }
    }

    private void initializeLoader()
            throws MalformedURLException {
        URL[] urls = { new URL("file://" + directory.getAbsolutePath() + "/") };
        loader = newInstance(urls);
    }

    @Override
    public void process()
            throws CompilationFailedException {
        loadClasses();
    }

    private void loadClasses() {
        List<Class> classes = listAllClasses(directory);
        LOGGER.log("Found {1} classes", classes.size());
        buildClassContexts(classes);
    }

    private List<Class> listAllClasses(File file) {
        List<Class> list = new ArrayList<>();

        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                List<Class> subList = listAllClasses(f);
                list.addAll(subList);
            }
        } else if (file.getName().endsWith(CLASS_EXTENSION)) {
            Class c = getClass(file);
            if (c != null) {
                LOGGER.log("Found class : {1}", c);
                list.add(c);
            }
        }

        return list;
    }

    private Class getClass(File file) {
        String className = getClassName(file);
        try {
            return loader.loadClass(className);
        } catch (ClassNotFoundException e) {
            LOGGER.log("Class '{1}' could not be found", className);
            return null;
        }
    }

    private String getClassName(File file) {
        return file.getAbsolutePath()
                .replace(directory.getAbsolutePath() + "/", "")
                .replace(".class", "")
                .replace('/', '.');
    }

    private void buildClassContexts(List<Class> classes) {
        for (Class c : classes) {
            ClassContextBuilder.build(context, c);
        }
    }
}
