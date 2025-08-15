package message.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/message")
public class MessageResource {
    @GET
    @Produces("text/plain")
    public Response doGet(){
        return Response.ok("Hello from Microprofile !!!").build();
    }
}
