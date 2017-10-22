package net.toregard.jerseydemo.restresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.ws.Response;


@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "admin")
@Path("/admin")
public class AdminResource {
    @GET
    @Produces("application/json")
    public String getname(String hallo) {

        return "hallo:"+hallo;
    }
}
