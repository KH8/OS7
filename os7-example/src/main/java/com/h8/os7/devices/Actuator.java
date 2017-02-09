package com.h8.os7.devices;

import com.h8.os7.core.annotations.dependency.Injectable;
import com.h8.os7.core.annotations.dependency.Runner;
import com.h8.os7.core.types.RunnerType;
import lombok.Setter;

public class Actuator {
    @Setter
    private boolean interlock;

    private Mode mode;

    @Injectable("exampleActuatorInterface")
    private ActuatorInterface i;

    public void moveForward() {
        mode = Mode.FORWARD;
    }

    public void moveBackward() {
        mode = Mode.BACKWARD;
    }

    public void stop() {
        mode = Mode.STOP;
    }

    @Runner(RunnerType.OB1)
    public void run() {
        handleActuatorMovement();
    }

    private void handleActuatorMovement() {
        i.getForwardMovement().set(false);
        i.getBackwardMovement().set(false);
        handleActuatorMode();
    }

    private void handleActuatorMode() {
        switch (mode) {
            case FORWARD:
                if (!i.getMaxPosition().get() && !interlock) {
                    i.getForwardMovement().set(true);
                }
                break;
            case BACKWARD:
                if (!i.getMinPosition().get() && !interlock) {
                    i.getBackwardMovement().set(true);
                }
                break;
            default:
                break;
        }
    }

    private enum Mode {
        FORWARD,
        BACKWARD,
        STOP
    }
}
