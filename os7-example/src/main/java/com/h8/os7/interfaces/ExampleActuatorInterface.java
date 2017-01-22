package com.h8.os7.interfaces;

import com.h8.os7.core.annotations.components.address.Address;
import com.h8.os7.core.annotations.components.structure.Interface;
import com.h8.os7.core.annotations.components.periphery.Input;
import com.h8.os7.components.input.impl.BooleanInput;
import com.h8.os7.core.types.InputType;
import com.h8.os7.core.annotations.components.periphery.Output;
import com.h8.os7.components.output.impl.BooleanOutput;
import com.h8.os7.core.types.OutputType;
import com.h8.os7.devices.ActuatorInterface;
import lombok.Getter;

@Interface("exampleActuatorInterface")
public class ExampleActuatorInterface implements ActuatorInterface {
    @Getter
    @Input(type = InputType.I, address = @Address(10.0))
    private BooleanInput minPosition;

    @Getter
    @Input(type = InputType.I, address = @Address(10.1))
    private BooleanInput maxPosition;

    @Getter
    @Input(type = InputType.I, address = @Address(10.2))
    private BooleanInput movementLocked;

    @Getter
    @Output(type = OutputType.A, address = @Address(10.0))
    private BooleanOutput forwardMovement;

    @Getter
    @Output(type = OutputType.A, address = @Address(10.1))
    private BooleanOutput backwardMovement;
}
