package net.toregard.jerseydemo.config;

import net.toregard.jerseydemo.restresources.AdminResource;
import net.toregard.jerseydemo.restresources.UserResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(UserResource.class);
        register(AdminResource.class);
    }
}
