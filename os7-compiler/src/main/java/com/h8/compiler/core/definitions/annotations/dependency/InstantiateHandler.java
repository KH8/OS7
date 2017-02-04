package com.h8.compiler.core.definitions.annotations.dependency;

import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.Instance;

import java.lang.reflect.Field;

class InstantiateHandler implements DependencyHandler {
    @Override
    public void handle(CompilationContext context, Instance i, Field f) {
        context.getInstances().add(getNewInstance(i, f));
    }

    private Instance getNewInstance(Instance parent, Field f) {
        Instance i = new Instance();
        i.setName(getNewInstanceName(parent, f));
        i.setC(f.getType());
        i.setDefinition(DependencyAnnotationDefinition.INSTANTIATE);
        return i;
    }

    private String getNewInstanceName(Instance parent, Field f) {
        return StringFormatter.format("{1}.{2}", parent.getName(), f.getName());
    }
}
