package net.toregard.jerseydemo.springstatemachine.statemachine;

import net.toregard.jerseydemo.springstatemachine.BusinessFault;
import net.toregard.jerseydemo.springstatemachine.CreateServiceRequest;
import org.springframework.statemachine.action.Action;

public class ActionStatesHandler {
    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> doServiceStateAction() {
        return ctx -> {
            CreateServiceRequest createServiceRequest =ctx.getExtendedState().get("CreateServiceRequest",CreateServiceRequest.class);
            if(createServiceRequest!=null || createServiceRequest.getId()!=null || createServiceRequest.getId().trim().equals("stateError")){
                System.out.println("doServiceStateAction not accepted " + ctx.getTarget().getId());
                throw new BusinessFault("error validation on createServiceRequest", DeploymentProcessTest.States.SERVICE_CREATED);
            }
            System.out.println("*********doServiceStateAction**************************");;
        };
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> doServiceStateErrorAction() {
        return ctx -> {
            System.out.println("*********doServiceStateErrorAction**************************");
            ctx.getStateMachine().sendEvent(DeploymentProcessTest.Events.SERVICE_STATE_ERROR_STOP);
        };
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> doServiceStateErrorEndAction() {
        return ctx -> {
            System.out.println("*********doServiceStateErrorEndAction**************************");
        };
    }
}
