package net.toregard.jerseydemo.springstatemachine.statemachine;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class HalloAction implements Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> {
    @Override
    public void execute(StateContext<DeploymentProcessTest.States, DeploymentProcessTest.Events> stateContext) {
        System.out.println("***********HalloAction********************");
    }
}
