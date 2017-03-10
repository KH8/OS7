package com.h8.os7.controllers;

import com.h8.os7.core.annotations.components.structure.Controller;
import com.h8.os7.core.annotations.execution.Runner;
import com.h8.os7.core.annotations.dependency.Use;
import com.h8.os7.core.types.RunnerType;
import com.h8.os7.interfaces.ExampleTankLevelInterface;

@Controller("tankController")
public class TankController {
    private static final int WARNING_LEVEL = 100;
    private static final int ALARM_LEVEL = 120;

    @Use
    private ExampleTankLevelInterface exampleTankLevelInterface;

    @Runner(RunnerType.OB1)
    public void run() {
        handleTankIndication();
    }

    private void handleTankIndication() {
        Boolean warningLevelReached = exampleTankLevelInterface.getLevelSensor().get() > WARNING_LEVEL;
        exampleTankLevelInterface.getYellowLevelLamp().set(warningLevelReached);

        Boolean alarmLevelReached = exampleTankLevelInterface.getLevelSensor().get() > ALARM_LEVEL;
        exampleTankLevelInterface.getRedLevelLamp().set(alarmLevelReached);
    }
}
