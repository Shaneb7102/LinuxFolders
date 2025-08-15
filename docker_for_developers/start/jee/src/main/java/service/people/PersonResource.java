package service.people;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



public class PersonResource {

    @Inject
    PersonDatabase database;

    public Person[] get() {
        return database.currentList();
    }

    @Path("{id}")
    public Person get(@PathParam("id") int id) {
        return database.getPerson(id);
    }
}
