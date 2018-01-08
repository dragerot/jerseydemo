package net.toregard.jerseydemo.springstatemachine;

import net.toregard.jerseydemo.springstatemachine.statemachine.DeploymentProcessTest;

public class BusinessFault extends RuntimeException {
    private final DeploymentProcessTest.States errorType;
    public BusinessFault(String message, DeploymentProcessTest.States errorType) {
        super(message);
        this.errorType = errorType;
    }
}
