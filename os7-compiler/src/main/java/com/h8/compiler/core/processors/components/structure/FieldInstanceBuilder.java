package com.h8.compiler.core.processors.components.structure;

import com.h8.compiler.common.Logger;
import com.h8.compiler.common.StringFormatter;
import com.h8.compiler.core.context.CompilationContext;
import com.h8.compiler.core.context.components.ClassContext;
import com.h8.compiler.core.context.components.FieldContext;
import com.h8.compiler.core.context.components.InstanceContext;
import com.h8.compiler.core.processors.AbstractProcessor;
import com.h8.compiler.exception.CompilationFailedException;

public class FieldInstanceBuilder extends AbstractProcessor {
    private static final Logger LOGGER = Logger.get(FieldInstanceBuilder.class);

    public FieldInstanceBuilder(CompilationContext context) {
        super(context);
    }

    private boolean newInstancesCreated = true;

    @Override
    public void process()
            throws CompilationFailedException {
        while (newInstancesCreated) {
            processAllClasses();
        }
    }

    private void processAllClasses()
            throws CompilationFailedException {
        newInstancesCreated = false;
        for (ClassContext cCtx : context.getClasses().values()) {
            processAllClassInstances(cCtx);
        }
    }

    private void processAllClassInstances(ClassContext cCtx)
            throws CompilationFailedException {
        for (InstanceContext iCtx : cCtx.getInstances().values()) {
            processAllClassInstanceFields(cCtx, iCtx);
        }
    }

    private void processAllClassInstanceFields(ClassContext cCtx, InstanceContext iCtx)
            throws CompilationFailedException {
        for (FieldContext fCtx : cCtx.getFields().values()) {
            processClassInstanceField(iCtx, fCtx);
        }
    }

    private void processClassInstanceField(InstanceContext iCtx, FieldContext fCtx)
            throws CompilationFailedException {
        amendFieldWithItsClassContext(fCtx);
        if (checkIfFieldShouldBeProcessed(fCtx)) {
            Class c = fCtx.getF().getType();
            ClassContext fcCtx = context.getClasses().get(c);
            registerNewInstance(iCtx, fCtx, fcCtx, c) ;
        }
    }

    private void amendFieldWithItsClassContext(FieldContext fCtx) {
        ClassContext cCtx = context.getClasses().get(fCtx.getF().getType());
        fCtx.setCCtx(cCtx);
    }

    private boolean checkIfFieldShouldBeProcessed(FieldContext fCtx) {
        return !fCtx.getParentCtx().getC().isEnum()
                && fCtx.getACtx().getInjectableAnnotation() == null
                && fCtx.getACtx().getUseAnnotation() == null;
    }

    private void registerNewInstance(InstanceContext iCtx, FieldContext fCtx, ClassContext fcCtx, Class c)
            throws CompilationFailedException {
        String name = getNewInstanceName(iCtx, fCtx);
        if (!context.getInstances().containsKey(name)) {
            InstanceContext fiCtx = fcCtx != null ?
                    new InstanceContext(name, fcCtx) : new InstanceContext(name, c);
            context.putInstance(fiCtx);
            addFieldInstanceToParent(iCtx, fiCtx);
            LOGGER.log("New instance for field '{1} [{2}] created'", name, fiCtx.getC().getSimpleName());
            newInstancesCreated = true;
        }
    }

    private String getNewInstanceName(InstanceContext i, FieldContext fCtx) {
        return StringFormatter.format("{1}.{2}", i.getName(), fCtx.getF().getName());
    }

    private void addFieldInstanceToParent(InstanceContext i, InstanceContext fi) {
        i.getFields().put(fi.getName(), fi);
    }
}
