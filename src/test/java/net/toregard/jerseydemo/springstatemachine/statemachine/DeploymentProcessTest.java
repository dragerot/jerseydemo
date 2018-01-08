package net.toregard.jerseydemo.springstatemachine.statemachine;

import net.toregard.jerseydemo.springstatemachine.CreateDeploymentRequest;
import net.toregard.jerseydemo.springstatemachine.CreateRouteRequest;
import net.toregard.jerseydemo.springstatemachine.CreateServiceRequest;
import org.junit.Test;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

public class DeploymentProcessTest {

    public enum States {
        START,
        DEPLOYCONFIG_CREATED, DEPLOYCONFIG_FAILED,
        SERVICE_CREATED,SERVICE_FAILED,
        ROUTE_CREATED,
        SAVED,
        ROUTE_FAILED, DEPLOYED
    }

    public enum Events {
        START_DEPLOY, FAILED_CREATE_SERVICE, FAILED_CREATE_ROUTE, FAILED_CREATE_DEPLOYCONFIG
    }

    StateMachineBuilder.Builder<States, Events> createstateProcessBuilder() throws Exception {
        Actionshandlers actionshandlers = new Actionshandlers();

        StateMachineBuilder.Builder<States, Events> builder
                = StateMachineBuilder.builder();
        builder.configureStates()
                .withStates()
                .initial(States.START)
                .state(States.DEPLOYCONFIG_CREATED)
                .state(States.DEPLOYCONFIG_FAILED)
                .state(States.SERVICE_CREATED)
                .state(States.SERVICE_FAILED)
                .state(States.ROUTE_CREATED)
                .state(States.ROUTE_FAILED)
                .state(States.SAVED)
                .end(States.DEPLOYED)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.START)
                .target(States.DEPLOYCONFIG_CREATED)
                .event(Events.START_DEPLOY)
                .action(actionshandlers.createDeploymentAction(), actionshandlers.createDeploymentErrorAction())
                .and()
                .withExternal()
                .source(States.START)
                .target(States.DEPLOYCONFIG_FAILED).event(Events.FAILED_CREATE_DEPLOYCONFIG)

                .and()
                .withExternal()
                .source(States.DEPLOYCONFIG_CREATED)
                .target(States.SERVICE_CREATED)
                .action(actionshandlers.createServiceAction(),actionshandlers.createServiceErrorAction())
                .and()
                .withExternal()
                .source(States.DEPLOYCONFIG_CREATED)
                .target(States.SERVICE_FAILED).event(Events.FAILED_CREATE_SERVICE)
                .action(actionshandlers.deleteDeploymentAction())
                .and()
                .withExternal()
                .source(States.SERVICE_FAILED)
                .target(States.DEPLOYCONFIG_FAILED)

                .and()
                .withExternal()
                .source(States.SERVICE_CREATED)
                .target(States.ROUTE_CREATED)
                .action(actionshandlers.createRouteAction(),actionshandlers.createRouteErrorAction())
                .and()
                .withExternal()
                .source(States.SERVICE_CREATED)
                .target(States.ROUTE_FAILED).event(Events.FAILED_CREATE_ROUTE)
                .action(actionshandlers.deleteServiceAction())
                .and()
                .withExternal()
                .source(States.ROUTE_FAILED)
                .target(States.SERVICE_FAILED)
                .action(actionshandlers.deleteDeploymentAction())

                .and()
                .withExternal()
                .source(States.ROUTE_CREATED)
                .target(States.SAVED)
                .action(actionshandlers.actionInState())
                .and()
                .withExternal()
                .source(States.SAVED)
                .target(States.DEPLOYED)
                .action(actionshandlers.actionInState());

        return builder;
    }

    @Test
    public void testhappy() throws Exception {
        StateMachine<States, Events> machine = createstateProcessBuilder().build();
        machine.getExtendedState().getVariables().put("CreateDeploymentRequest", CreateDeploymentRequest.builder().id("deployId").build());
        machine.getExtendedState().getVariables().put("CreateServiceRequest", CreateServiceRequest.builder().id("serviceid").build());
        machine.getExtendedState().getVariables().put("CreateRouteRequest", CreateRouteRequest.builder().id("routeId").build());

        machine.start();
        machine.sendEvent(Events.START_DEPLOY);
        assertThat(machine.getState().getId()).isEqualTo(States.DEPLOYED);
    }

    @Test
    public void failedCreateDeploymentConfigRequest() throws Exception {
        StateMachine<States, Events> machine = createstateProcessBuilder().build();
        machine.getExtendedState().getVariables().put("CreateDeploymentRequest", CreateDeploymentRequest.builder().id(null).build());
        machine.start();
        machine.sendEvent(Events.START_DEPLOY);
        assertThat(machine.getState().getId()).isEqualTo(States.DEPLOYCONFIG_FAILED);
    }

    @Test
    public void failedCreateServiceRequest() throws Exception {
        StateMachine<States, Events> machine = createstateProcessBuilder().build();
        machine.getExtendedState().getVariables().put("CreateDeploymentRequest", CreateDeploymentRequest.builder().id("deployId").build());
        machine.getExtendedState().getVariables().put("CreateServiceRequest", CreateServiceRequest.builder().id(null).build());
        machine.start();
        machine.sendEvent(Events.START_DEPLOY);
        assertThat(machine.getState().getId()).isEqualTo(States.DEPLOYCONFIG_FAILED);
    }

    @Test
    public void failedCreateRouteRequest() throws Exception {
        StateMachine<States, Events> machine = createstateProcessBuilder().build();
        machine.getExtendedState().getVariables().put("CreateDeploymentRequest", CreateDeploymentRequest.builder().id("deployId").build());
        machine.getExtendedState().getVariables().put("CreateServiceRequest", CreateServiceRequest.builder().id("serviceid").build());
        machine.getExtendedState().getVariables().put("CreateRouteRequest", CreateRouteRequest.builder().id(null).build());
        machine.start();
        machine.sendEvent(Events.START_DEPLOY);
        assertThat(machine.getState().getId()).isEqualTo(States.DEPLOYCONFIG_FAILED);
    }

    private State<States, Events> getState(StateMachine<States, Events> machine) {
        return machine.getState();
    }


}
