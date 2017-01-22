package com.h8.os7.interfaces;

import com.h8.os7.core.annotations.components.address.Address;
import com.h8.os7.core.annotations.components.structure.Interface;
import com.h8.os7.core.annotations.components.periphery.Input;
import com.h8.os7.components.input.impl.BooleanInput;
import com.h8.os7.core.types.InputType;
import lombok.Getter;

@Interface("controlDeskInterface")
public class ExampleControlDeskInterface {
    @Getter
    @Input(type = InputType.I, address = @Address(12.0))
    private BooleanInput forwardButton;

    @Getter
    @Input(type = InputType.I, address = @Address(12.1))
    private BooleanInput backwardButton;

    @Getter
    @Input(type = InputType.I, address = @Address(12.2))
    private BooleanInput stopButton;
}
