package net.toregard.jerseydemo.restresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/ping")
public class PingResource {
    @GET
    @Path("/")
    @Produces("application/json")
    public Response get() {
        return Response.ok().build();
    }
}
