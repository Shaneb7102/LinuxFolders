package message;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/message")
public class MessageResource {

    @Inject
    private MessageService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public String message(@PathParam("name") String name) {
        return service.getMessage(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String message() {
        return "hello";
    }
}