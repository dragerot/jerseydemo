package net.toregard.jerseydemo.springstatemachine;

import org.junit.Test;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * @See http://www.baeldung.com/spring-state-machine
 */
public class DeploymentStatemachine {

    public enum States {
        STATE_START_DEPLOY,
        STATE_HAVE_CREATED_DEPLOYMENT_CONFIG,
        STATE_HAVE_CREATED_SERVICE,
        STATE_HAVE_CREATED_ROUTE,
        END_STATE_FAILED_ON_DEPLOYMENT_CONFIG,
        END_STATE_FAILED_ON_SERVICE,
        END_STATE_FAILED_ON_ROUTE,
        HAPPY_END
    }

    public enum Events {
        EVENT_CREATE_DEPLOYMENT,
        EVENT_FAIL_ON_CREATE_DEPLOYMENT,
        EVENT_CREATE_SERVICE,
        EVENT_FAIL_ON_CREATE_SERVICE,
        EVENT_CREATE_ROUTE,
        EVENT_FAIL_ON_CREATE_ROUTE, EVENT_HAPPY_END,
    }

    @Test
    public void test1() throws Exception {

        //Define states

        StateMachineBuilder.Builder<States, Events> builder
                = StateMachineBuilder.builder();
        builder.configureStates()
                .withStates()
                .initial(States.STATE_START_DEPLOY, event_start_Action())
                .state(States.STATE_HAVE_CREATED_DEPLOYMENT_CONFIG)
                .choice(States.STATE_HAVE_CREATED_SERVICE)
                .state(States.STATE_HAVE_CREATED_ROUTE)
                .end(States.END_STATE_FAILED_ON_DEPLOYMENT_CONFIG)
                .end(States.HAPPY_END)
                .states(EnumSet.allOf(States.class));

        //Define transition events
        builder.configureTransitions()
                .withExternal()
                .source(States.STATE_START_DEPLOY)
                .target(States.STATE_HAVE_CREATED_DEPLOYMENT_CONFIG).event(Events.EVENT_CREATE_DEPLOYMENT)
                .guard(guard_create_deployment_Action())
                .and()
                .withInternal()
                 .action(doSomethingIntheDeploymentState()).event(Events.EVENT_CREATE_DEPLOYMENT)
                .and()
                .withExternal()
                .source(States.STATE_HAVE_CREATED_DEPLOYMENT_CONFIG)
                .target(States.STATE_HAVE_CREATED_SERVICE).event(Events.EVENT_CREATE_SERVICE)
                .guard(guard_create_serviceAction())
                .and()
                .withChoice()
                .source(States.STATE_HAVE_CREATED_SERVICE)
                .first(States.STATE_HAVE_CREATED_ROUTE,guard_create_routeAction())
                .last(States.END_STATE_FAILED_ON_ROUTE,errorAction())
                .and()
                .withExternal()
                .source(States.STATE_HAVE_CREATED_ROUTE)
                .target(States.HAPPY_END).event(Events.EVENT_HAPPY_END)
                .action(event_happyEndAction());

        //Logging(?)
        //builder.configureConfiguration().withConfiguration().listener(new StateMachineListener());

        //Kjør test
        StateMachine<States, Events> machine = builder.build();
        machine.start();
        machine.getExtendedState().getVariables().put("CreateDeploymentRequest",CreateDeploymentRequest.builder().id("deployId").build());
        machine.getExtendedState().getVariables().put("CreateServiceRequest",CreateServiceRequest.builder().id("serviceid").build());
        machine.getExtendedState().getVariables().put("CreateRouteRequest",CreateRouteRequest.builder().id("routeId").build());
        machine.sendEvent(Events.EVENT_CREATE_DEPLOYMENT);
        //machine.sendEvent(Events.EVENT_CREATE_SERVICE);
        //machine.sendEvent(Events.EVENT_CREATE_ROUTE);
        State state =machine.getState();
        //machine.sendEvent(Events.EVENT_HAPPY_END);
    }


    public Action<States, Events> errorAction() {
        return ctx -> System.out.println(
                "Error " + ctx.getSource().getId() + ctx.getException());
    }

    public Action<States, Events> event_start_Action() {
        return ctx ->{
            System.out.println("event_start_Action " + ctx.getTarget().getId());

        };
    }


    public Action<States, Events> doSomethingIntheDeploymentState() {
        return ctx -> {
            System.out.println("doSomethingIntheDeploymentState " + ctx.getSource().getId());
            ctx.getStateMachine().sendEvent(Events.EVENT_CREATE_SERVICE);
        };
    }

    public Guard<States, Events> guard_create_deployment_Action() {
        return ctx -> {
            System.out.println("guard_create_deployment_Action " + ctx.getTarget().getId());
            CreateDeploymentRequest createDeploymentRequest =ctx.getExtendedState().get("CreateDeploymentRequest",CreateDeploymentRequest.class);
            if(createDeploymentRequest==null || createDeploymentRequest.getId().trim().length()==0) return false;
            System.out.println("Input CreateDeploymentRequest id: " + createDeploymentRequest.getId());
            return true;
        };

    }

    public Guard<States, Events> guard_create_serviceAction() {
        return ctx -> {
            System.out.println("guard_create_serviceAction " + ctx.getTarget().getId());
            CreateServiceRequest createServiceRequest =ctx.getExtendedState().get("CreateServiceRequest",CreateServiceRequest.class);
            if(createServiceRequest==null || createServiceRequest.getId().trim().length()==0) return false;
            System.out.println("Input CreateServiceRequest id: " + createServiceRequest.getId());
            return true;
        };
    }

    public Guard<States, Events> guard_create_routeAction() {

        return ctx -> {
            System.out.println("guard_create_routeAction " + ctx.getTarget().getId());
            CreateRouteRequest createRouteRequest =ctx.getExtendedState().get("CreateRouteRequest",CreateRouteRequest.class);
            if(createRouteRequest==null || createRouteRequest.getId().trim().length()==0) return false;
            System.out.println("Input CreateRouteRequest id: " + createRouteRequest.getId());
            return true;
        };
    }

    public Action<States, Events> event_happyEndAction() {

        return ctx -> System.out.println("event_happyEndAction " + ctx.getTarget().getId());
    }
}
