package message;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MessageController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/message")
    public Message greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Message(counter.incrementAndGet(),
                String.format(template, name));
    }
}
