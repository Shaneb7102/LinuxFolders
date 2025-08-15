package message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MessageController {
    private static final String template = "Hello, %s!";


    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/message")
    public Message greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Message(counter.incrementAndGet(),
                String.format(template, name));
    }

    @GetMapping("/v2/message")
    public String getMessageV2() {
        return "This is version 2 of the message";
    }
}
