package net.toregard.jerseydemo.springstatemachine.statemachine;

import net.toregard.jerseydemo.springstatemachine.BusinessFault;
import net.toregard.jerseydemo.springstatemachine.CreateDeploymentRequest;
import net.toregard.jerseydemo.springstatemachine.CreateRouteRequest;
import net.toregard.jerseydemo.springstatemachine.CreateServiceRequest;
import net.toregard.jerseydemo.springstatemachine.statemachine.DeploymentProcessTest;

public class SimulateOpenshiftServices {
    public static final String FAILED ="failed";
    public void createDeploymentConfigService(CreateDeploymentRequest request) {
        System.out.println("Run createDeploymentConfigService");
        if (request.getId().equals(FAILED)) {
            throw new BusinessFault("Error createDeploymentConfigService", DeploymentProcessTest.States.DEPLOYCONFIG_CREATED);
        }
    }

    public void deleteDeploymentConfigService(CreateDeploymentRequest request) {
        System.out.println("Run deleteDeploymentConfigService");
    }

    public void createService(CreateServiceRequest request) {
        System.out.println("Run createService");
        if (request.getId().equals(FAILED)) {
            throw new BusinessFault("Error CreateServiceRequest", DeploymentProcessTest.States.SERVICE_CREATED);
        }
    }

    public void deleteServiceService(CreateServiceRequest request) {
        System.out.println("Run delete Service");
    }

    public void createRouteService(CreateRouteRequest request) {
        System.out.println("Run CreateRouteRequest");
        if (request.getId().equals(FAILED)) {
            throw new BusinessFault("Error CreateRouteRequest", DeploymentProcessTest.States.ROUTE_CREATED);
        }
    }

    public void save(String request) {
        System.out.println("Run saveService");
        if (request.equals(FAILED)) {
            throw new BusinessFault("Error save", DeploymentProcessTest.States.SAVED);
        }
    }
}
