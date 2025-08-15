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

    @Value("${translator.url}")
    private String translatorURL;


    /*
    @RequestMapping("/message")
    public Message greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Message(counter.incrementAndGet(),
                String.format(template, name));
    }
    */

    @RequestMapping("/message")
    public Message greeting(
            @RequestParam(value = "name", defaultValue = "World") String name,
            @RequestParam(required = false) String lang) {

        String baseText = String.format(template, name);
        Message baseMessage = new Message(counter.incrementAndGet(), baseText);

        // If no lang param, return base message
        if (lang == null || lang.isBlank()) {
            return baseMessage;
        }

        // If there is a lang param, translate and return translated message
        return restTemplate.getForObject(
                UriComponentsBuilder.fromHttpUrl(translatorURL)
                        .queryParam("text", baseMessage.getMessage())
                        .queryParam("lang", lang)
                        .toUriString(),
                Message.class
        );

    }
}
