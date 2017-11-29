package net.toregard.jerseydemo.restresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlAccessorType(XmlAccessType.NONE)
//@XmlRootElement(name = "deploy")
//@Path("/deploy")
public class DeployResource {
//    @GET
//    @Produces("application/json")
    public Response getDeploys() {
        return Response.status(201).status(Response.Status.ACCEPTED).build();

    }
}
