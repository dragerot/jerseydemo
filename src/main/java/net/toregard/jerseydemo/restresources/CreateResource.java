package net.toregard.jerseydemo.restresources;

import net.toregard.jerseydemo.domain.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.net.URISyntaxException;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "create")
@Path("/create")
public class CreateResource {

    @POST
    @Consumes("application/json")
    public Response createUser(User user) throws URISyntaxException
    {
        if(user.getFirstName() == null || user.getLastName() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        user.setId(1);
        user.setUri("/user-management/"+user.getId());
        //DB.put(user.getId(), user);
        return Response.status(201).contentLocation(new URI(user.getUri())).build();
    }
}
