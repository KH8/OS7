package com.h8.os7.interfaces;

import com.h8.os7.core.annotations.components.address.Address;
import com.h8.os7.core.annotations.components.peripheries.Input;
import com.h8.os7.components.input.impl.WordInput;
import com.h8.os7.core.types.InputType;
import com.h8.os7.core.annotations.components.peripheries.Output;
import com.h8.os7.components.output.impl.BooleanOutput;
import com.h8.os7.core.types.OutputType;
import lombok.Getter;

public class ExampleTankLevelInterface {
    @Getter
    @Input(type = InputType.I, address = @Address(22))
    private WordInput levelSensor;

    @Getter
    @Output(type = OutputType.A, address = @Address(20.0))
    private BooleanOutput yellowLevelLamp;


    @Getter
    @Output(type = OutputType.A, address = @Address(20.1))
    private BooleanOutput redLevelLamp;
}
