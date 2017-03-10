package com.h8.os7.components.input;

import com.h8.os7.core.annotations.execution.Runner;
import com.h8.os7.components.common.Callback;
import com.h8.os7.components.common.Periphery;
import com.h8.os7.core.types.RunnerType;

/**
 * Input periphery component
 * @param <T>
 *     Generic type adequate to S7 type
 */
public abstract class Input<T> extends Periphery<T> {
    protected T oldValue;
    protected Boolean onValueChanged;

    /**
     * Getter of input value
     * @return
     *      Input value
     */
    public T get() {
        return value;
    }

    /**
     * An event handler can be defined at value change
     * @param callback
     *      Method to be called when event occurs
     */
    public void onValueChanged(Callback<T> callback) {}

    @Runner(RunnerType.OB1)
    protected void evaluate() {
        onValueChanged = !value.equals(oldValue);
        oldValue = value;
    }
}
