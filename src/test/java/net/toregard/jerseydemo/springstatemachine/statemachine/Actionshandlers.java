package net.toregard.jerseydemo.springstatemachine.statemachine;

import net.toregard.jerseydemo.springstatemachine.BusinessFault;
import net.toregard.jerseydemo.springstatemachine.CreateDeploymentRequest;
import net.toregard.jerseydemo.springstatemachine.CreateRouteRequest;
import net.toregard.jerseydemo.springstatemachine.CreateServiceRequest;
import org.springframework.statemachine.action.Action;

public class Actionshandlers {

    SimulateOpenshiftServices service=null;

    public Actionshandlers() {
        this.service = new SimulateOpenshiftServices();
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> createDeploymentAction() {
        return ctx -> {
            CreateDeploymentRequest createDeploymentRequest =ctx.getExtendedState().get("CreateDeploymentRequest",CreateDeploymentRequest.class);
            if(createDeploymentRequest==null || createDeploymentRequest.getId()==null || createDeploymentRequest.getId().trim().length()==0){
                System.out.println("validateCreateDeployment not accepted " + ctx.getTarget().getId());
                throw new BusinessFault("error validation on createDeploymentAction", DeploymentProcessTest.States.DEPLOYCONFIG_CREATED);
            }
            //Call service
            System.out.println("validateCreateDeployment accepted " + ctx.getTarget().getId());
            service.createDeploymentConfigService(createDeploymentRequest);
        };
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> createDeploymentErrorAction() {
        return ctx -> {
            System.out.println("createDeploymentErrorAction errorhandling " + ctx.getTarget().getId());
            ctx.getStateMachine().sendEvent(DeploymentProcessTest.Events.FAILED_CREATE_DEPLOYCONFIG);
        };
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> deleteDeploymentAction() {
        return ctx -> {
            System.out.println("deleteDeploymentAction " + ctx.getTarget().getId());
            CreateDeploymentRequest createDeploymentRequest =ctx.getExtendedState().get("CreateDeploymentRequest",CreateDeploymentRequest.class);
            service.deleteDeploymentConfigService(createDeploymentRequest);
        };
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> deleteServiceAction() {
        return ctx -> {
            System.out.println("deleteServiceAction " + ctx.getTarget().getId());
            CreateServiceRequest createServiceRequest =ctx.getExtendedState().get("CreateServiceRequest",CreateServiceRequest.class);
            service.deleteServiceService(createServiceRequest);

        };
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> createServiceAction() {
        return ctx -> {
            CreateServiceRequest createServiceRequest =ctx.getExtendedState().get("CreateServiceRequest",CreateServiceRequest.class);
            if(createServiceRequest==null || createServiceRequest.getId()==null || createServiceRequest.getId().trim().length()==0){
                System.out.println("validatecreateServiceRequest not accepted " + ctx.getTarget().getId());
                throw new BusinessFault("error validation on createServiceRequest", DeploymentProcessTest.States.SERVICE_CREATED);
            }
            //Call service
            System.out.println("validateCreateService accepted " + ctx.getTarget().getId());
            service.createService(createServiceRequest);
        };
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> createServiceErrorAction() {
        return ctx -> {
            System.out.println("createServiceErrorAction errorhandling " + ctx.getTarget().getId());
            ctx.getStateMachine().sendEvent(DeploymentProcessTest.Events.FAILED_CREATE_SERVICE);
        };
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> createRouteAction() {
        return ctx -> {
            CreateRouteRequest createRouteRequest =ctx.getExtendedState().get("CreateRouteRequest", CreateRouteRequest.class);
            if(createRouteRequest==null || createRouteRequest.getId()==null || createRouteRequest.getId().trim().length()==0){
                System.out.println("createRouteAction not accepted " + ctx.getTarget().getId());
                throw new BusinessFault("error validation on createRouteAction", DeploymentProcessTest.States.ROUTE_CREATED);
            }
            //Call service
            System.out.println("validate createRouteAction accepted " + ctx.getTarget().getId());
            service.createRouteService(createRouteRequest);
        };
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> createRouteErrorAction() {
        return ctx -> {
            System.out.println("createRouteErrorAction errorhandling " + ctx.getTarget().getId());
            ctx.getStateMachine().sendEvent(DeploymentProcessTest.Events.FAILED_CREATE_ROUTE);
        };
    }

    public Action<DeploymentProcessTest.States, DeploymentProcessTest.Events> actionInState() {
        return ctx -> {
            String format = String.format("****actionInState %s target %s", ctx.getSource().getId(),ctx.getTarget().getId());
            System.out.println(format);
        };
    }

}
