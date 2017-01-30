package com.h8.compiler.core.context.processor;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import static java.net.URLClassLoader.newInstance;

public class ClassFileLoader extends AbstractProcessor {
    private static final String CLASS_EXTENSION = ".class";
    private static final String TARGET_CLASSES_LOCATION = "/target/classes/";

    private File directory;
    private URLClassLoader loader;

    public ClassFileLoader(CompilationContext context) {
        super(context);
    }

    @Override
    public void process()
            throws Exception {
        directory = new File(context.getWorkingDirectory() + TARGET_CLASSES_LOCATION);
        initialize();
        loadClasses();
    }

    private void initialize()
            throws FileNotFoundException, MalformedURLException {
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

    private void loadClasses() {
        List<Class> classList = listAllClasses(directory);
        Logger.log(ClassFileLoader.class, "Found {1} classes", classList.size());
        context.setClassList(classList);
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
            Logger.log(ClassFileLoader.class, "Class '{1}' could not be found", className);
            return null;
        }
    }

    private String getClassName(File file) {
        return file.getAbsolutePath()
                .replace(directory.getAbsolutePath() + "/", "")
                .replace(".class", "")
                .replace('/', '.');
    }
}
