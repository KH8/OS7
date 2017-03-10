package com.h8.os7.components.input.impl;

import com.h8.os7.components.input.Input;
import com.h8.os7.core.annotations.execution.Runner;
import com.h8.os7.components.common.Callback;
import com.h8.os7.core.types.RunnerType;

/**
 * Boolean input periphery component
 */
public class BooleanInput extends Input<Boolean> {
    protected Boolean onPositiveEdge;
    protected Boolean onNegativeEdge;

    /**
     * An event handler can be defined at positive edge
     * @param callback
     *      Method to be called when event occurs
     */
    public void onPositiveEdge(Callback<Boolean> callback) {}

    /**
     * An event handler can be defined at negative edge
     * @param callback
     *      Method to be called when event occurs
     */
    public void onNegativeEdge(Callback<Boolean> callback) {}

    @Override
    @Runner(RunnerType.OB1)
    protected void evaluate() {
        onPositiveEdge = value && !oldValue && !onPositiveEdge;
        onNegativeEdge = !value && oldValue && !onNegativeEdge ;
        super.evaluate();
    }
}
