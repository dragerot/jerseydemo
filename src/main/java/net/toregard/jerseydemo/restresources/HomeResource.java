package net.toregard.jerseydemo.restresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/ping")
public class HomeResource {
    @GET
    @Produces("application/json")
    public String home() {
        return "ok";
    }
}
