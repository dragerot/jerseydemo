package net.toregard.jerseydemo.restresources;

import net.toregard.jerseydemo.domain.User;
import net.toregard.jerseydemo.work.readproperties.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "admin")
@Path("/properties")
public class PropertiesResource {
    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @GET
    @Path("/{prefix}")
    @Produces("application/json")
    public Response getPrefix(@PathParam("prefix") String prefix)
    {
        Map<String,Object> mapped =Worker.getPropertiesStartingWith(configurableEnvironment,prefix);
        return Response
                .status(200)
                .entity(mapped).build();
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public Response get()
    {
        Map<String,Object> all =Worker.getAllProperties(configurableEnvironment);
        return Response
                .status(200)
                .entity(all).build();
    }
}
