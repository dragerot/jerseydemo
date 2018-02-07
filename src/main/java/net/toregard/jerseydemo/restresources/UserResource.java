package net.toregard.jerseydemo.restresources;

import net.toregard.jerseydemo.domain.User;
import net.toregard.jerseydemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
@Path("/users")
public class UserResource
{
    private static Map<Integer, User> DB = new HashMap<>();
    @Autowired
    UserService userService;

    @POST
    @Consumes("application/json")
    public Response createUser(@RequestBody User user) throws URISyntaxException
    {
        if(user.getFirstName() == null || user.getLastName() == null) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
        return Response
                .status(200)
                .entity(getDummyUser())
                .contentLocation(new URI("/user-management/"+getDummyUser().getSsn())).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getUserById(@PathParam("id") int id) throws URISyntaxException
    {
        User user = DB.get(id);
        if(user == null) {
            return Response.status(404).build();
        }
        return Response
                .status(200)
                .entity(user)
                .contentLocation(new URI("/user-management/"+id)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(@PathParam("id") int id, User user) throws URISyntaxException
    {
        return Response.status(200).entity(getDummyUser()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) throws URISyntaxException {
        return Response.status(404).build();
    }

    private User getDummyUser(){
        return User
                .builder()
                .ssn("0323231234")
                .firstName("toge ere")
                .lastName("Siste")
                .build();
    }

}
