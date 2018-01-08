package net.toregard.jerseydemo.springstatemachine.statemachine;

import net.toregard.jerseydemo.springstatemachine.CreateDeploymentRequest;
import org.springframework.statemachine.guard.Guard;

public class Guardshandlers {

    public Guard<DeploymentProcessTest.States, DeploymentProcessTest.Events> validateCreateDeploymentGuard() {
        return ctx -> {
            System.out.println("validateCreateDeploymentGuard " + ctx.getTarget().getId());
            CreateDeploymentRequest createDeploymentRequest =ctx.getExtendedState().get("CreateDeploymentRequest",CreateDeploymentRequest.class);
            if(createDeploymentRequest==null || createDeploymentRequest.getId().trim().length()==0){
                System.out.println("validateCreateDeploymentGuard not accepted " + ctx.getTarget().getId());
                return false;
            }
            System.out.println("validateCreateDeploymentGuard  accepted " + ctx.getTarget().getId());
            return true;
        };
    }
}
