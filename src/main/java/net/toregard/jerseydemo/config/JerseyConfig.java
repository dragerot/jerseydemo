package net.toregard.jerseydemo.config;

import net.toregard.jerseydemo.restresources.AdminResource;
import net.toregard.jerseydemo.restresources.HomeResource;
import net.toregard.jerseydemo.restresources.UserResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {


    public JerseyConfig() {
        register(UserResource.class);
        register(AdminResource.class);
        register(HomeResource.class);
    }
}
