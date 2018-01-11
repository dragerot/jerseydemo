package net.toregard.jerseydemo.springstatemachine.statemachine;

import net.toregard.jerseydemo.springstatemachine.CreateDeploymentRequest;
import net.toregard.jerseydemo.springstatemachine.CreateRouteRequest;
import net.toregard.jerseydemo.springstatemachine.CreateServiceRequest;
import org.junit.Test;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

public class DeploymentProcessTest {

    public enum States {
        START,
        DEPLOYMENT_FAILED,
        DEPLOYCONFIG_CREATED, DEPLOYCONFIG_DELETED,
        SERVICE_CREATED, SERVICE_DELETED,
        ROUTE_CREATED, ROUTE_DELETED,
        SAVED, SAVE_DELETED,
        HALLO_ERROR, NETTOPP_ERROR
    }

    public enum Events {
        START_DEPLOY,
        CREATE_DEPLOY_CONFIG,
        CREATE_SERVICE,
        CREATE_ROUTE, SAVE,
        FAILED_CREATE_DEPLOYCONFIG,
        FAILED_CREATE_SERVICE,
        FAILED_CREATE_ROUTE,
        DELETE_SERVICE, DELETE_DEPLOYCONFIG,
        UNHAPPY_DEPLOYMENT
    }

    public Action<States, Events> signal(DeploymentProcessTest.Events event) {
        return ctx -> {
            ctx.getStateMachine().sendEvent(event);
        };
    }

    StateMachineBuilder.Builder<States, Events> createstateProcessBuilder() throws Exception {
        Actionshandlers actionshandlers = new Actionshandlers();
        StateMachineBuilder.Builder<States, Events> builder
                = StateMachineBuilder.builder();
        builder.configureStates()
                .withStates()
                .initial(States.START)
                .state(States.DEPLOYCONFIG_CREATED)
                .state(States.SERVICE_CREATED)
                .state(States.ROUTE_CREATED)
                .state(States.SAVED)

                .state(States.DEPLOYMENT_FAILED)
                .state(States.DEPLOYCONFIG_DELETED)
                .state(States.SERVICE_DELETED)
                .state(States.ROUTE_DELETED)
                .end(States.SAVE_DELETED)
                .states(EnumSet.allOf(States.class));

        builder.configureTransitions()
                .withExternal()
                .source(States.START)
                .target(States.DEPLOYCONFIG_CREATED)
                .event(Events.START_DEPLOY)
                .action(actionshandlers.createDeploymentAction(), signal(Events.FAILED_CREATE_DEPLOYCONFIG))
                .and()
                .withExternal()
                .source(States.DEPLOYCONFIG_CREATED)
                .target(States.SERVICE_CREATED)
                .action(actionshandlers.createServiceAction(), signal(Events.FAILED_CREATE_SERVICE))
                .and()
                .withExternal()
                .source(States.SERVICE_CREATED)
                .target(States.ROUTE_CREATED)
                .action(actionshandlers.createRouteAction(), signal(Events.FAILED_CREATE_ROUTE))
                .and()
                .withExternal()
                .source(States.ROUTE_CREATED)
                .target(States.SAVED)
                .action(actionshandlers.actionInState())

                //ERROR
                .and()
                .withExternal()
                .source(States.START)
                .target(States.DEPLOYMENT_FAILED).event(Events.FAILED_CREATE_DEPLOYCONFIG)
                .action(c -> System.out.println("STOPP:FAILED_CREATE_DEPLOYCONFIG->DEPLOYMENT_FAILED"))
                .and()
                .withExternal()
                .source(States.DEPLOYCONFIG_CREATED)
                .target(States.DEPLOYMENT_FAILED).event(Events.FAILED_CREATE_SERVICE)
                //.action(c -> actionshandlers.deleteDeploymentAction())
                .action(c -> {
                    System.out.println("STOPP:deleteDeploymentAction->deleteDeploymentAction");
                } )
                .and()
                .withExternal()
                .source(States.ROUTE_DELETED)
                .target(States.SERVICE_DELETED).event(Events.DELETE_SERVICE)
                .action(actionshandlers.deleteServiceAction())
                .action(c -> c.getStateMachine().sendEvent(Events.DELETE_SERVICE))
                .and()
                .withExternal()
                .source(States.SERVICE_DELETED)
                .target(States.DEPLOYCONFIG_DELETED).event(Events.DELETE_DEPLOYCONFIG)
                .action(actionshandlers.deleteDeploymentAction())
                .action(c -> c.getStateMachine().sendEvent(Events.UNHAPPY_DEPLOYMENT))
                .and()
                .withExternal()
                .source(States.DEPLOYCONFIG_DELETED)
                .target(States.DEPLOYMENT_FAILED).event(Events.UNHAPPY_DEPLOYMENT);


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
        assertThat(machine.getState().getId()).isEqualTo(States.SAVED);
    }

    @Test
    public void failedCreateDeploymentConfigRequest() throws Exception {
        StateMachine<States, Events> machine = createstateProcessBuilder().build();
        machine.getExtendedState().getVariables().put("CreateDeploymentRequest", CreateDeploymentRequest.builder().id(null).build());
        machine.start();
        machine.sendEvent(Events.START_DEPLOY);
        assertThat(machine.getState().getId()).isEqualTo(States.DEPLOYMENT_FAILED);
    }

    @Test
    public void failedCreateServiceRequest() throws Exception {
        StateMachine<States, Events> machine = createstateProcessBuilder().build();
        machine.getExtendedState().getVariables().put("CreateDeploymentRequest", CreateDeploymentRequest.builder().id("deployId").build());
        machine.getExtendedState().getVariables().put("CreateServiceRequest", CreateServiceRequest.builder().id(null).build());
        machine.start();
        machine.sendEvent(Events.START_DEPLOY);
        assertThat(machine.getState().getId()).isEqualTo(States.DEPLOYMENT_FAILED);
    }
//
//    @Test
//    public void failedCreateRouteRequest() throws Exception {
//        StateMachine<States, Events> machine = createstateProcessBuilder().build();
//        machine.getExtendedState().getVariables().put("CreateDeploymentRequest", CreateDeploymentRequest.builder().id("deployId").build());
//        machine.getExtendedState().getVariables().put("CreateServiceRequest", CreateServiceRequest.builder().id("serviceid").build());
//        machine.getExtendedState().getVariables().put("CreateRouteRequest", CreateRouteRequest.builder().id(null).build());
//        machine.start();
//        machine.sendEvent(Events.START_DEPLOY);
//        assertThat(machine.getState().getId()).isEqualTo(States.DEPLOYCONFIG_FAILED);
//    }
//
//    @Test
//    public void failedOnServiceNode() throws Exception {
//        StateMachine<States, Events> machine = createstateProcessBuilder().build();
//        machine.getExtendedState().getVariables().put("CreateDeploymentRequest", CreateDeploymentRequest.builder().id("deployId").build());
//        machine.getExtendedState().getVariables().put("CreateServiceRequest", CreateServiceRequest.builder().id("stateError").build());
//        machine.start();
//        machine.sendEvent(Events.START_DEPLOY);
//        State<States,Events> state = machine.getState();
//        assertThat(state.getId()).isEqualTo(States.SERVICE_STATE_ERROR_STOP);
//    }
//


    private State<States, Events> getState(StateMachine<States, Events> machine) {
        return machine.getState();
    }


}
