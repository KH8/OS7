package com.h8.os7.controllers;

import com.h8.os7.core.annotations.components.structure.Controller;
import com.h8.os7.core.annotations.dependency.Inject;
import com.h8.os7.core.annotations.execution.Runner;
import com.h8.os7.core.annotations.dependency.Use;
import com.h8.os7.core.types.RunnerType;
import com.h8.os7.devices.Actuator;
import com.h8.os7.interfaces.ExampleActuatorInterface;
import com.h8.os7.interfaces.ExampleControlDeskInterface;

@Controller("actuatorController")
public class ActuatorController {
    @Use
    private ExampleActuatorInterface exampleActuatorInterface;

    @Use
    private ExampleControlDeskInterface exampleControlDeskInterface;

    @Inject("exampleActuatorInterface")
    private Actuator actuator;

    @Runner(RunnerType.OB1)
    public void run() {
        handleExampleActuator();
    }

    private void handleExampleActuator() {
        actuator.setInterlock(exampleActuatorInterface.getMovementLocked().get());

        if (exampleControlDeskInterface.getForwardButton().get()) {
            actuator.moveForward();
        }
        if (exampleControlDeskInterface.getBackwardButton().get()) {
            actuator.moveBackward();
        }
        if (exampleControlDeskInterface.getStopButton().get()) {
            actuator.stop();
        }
    }
}
