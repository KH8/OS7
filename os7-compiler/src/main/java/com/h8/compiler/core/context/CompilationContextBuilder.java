package com.h8.compiler.core.context;

import com.h8.compiler.common.Logger;
import com.h8.compiler.core.context.processor.ClassFileLoader;
import com.h8.compiler.core.context.processor.ComponentInstanceFinder;

public class CompilationContextBuilder extends CompilationContext {
    public CompilationContextBuilder() {}

    public void buildForWorkingDirectory(String wd) {
        this.setWorkingDirectory(wd);
        Logger.log(CompilationContextBuilder.class, "Working directory: {1}", wd);
        build();
    }

    private void build() {
        try {
            buildClassList();
            buildComponentInstances();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildClassList()
            throws Exception {
        new ClassFileLoader(this).process();
    }

    private void buildComponentInstances()
            throws Exception {
        new ComponentInstanceFinder(this).process();
    }
}
