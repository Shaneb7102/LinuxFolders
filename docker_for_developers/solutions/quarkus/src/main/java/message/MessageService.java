package message;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageService {

    public String getMessage(String name) {
        return "hello " + name + "\n";
    }

}
