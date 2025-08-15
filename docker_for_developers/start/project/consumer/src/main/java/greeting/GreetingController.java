package greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
public class GreetingController {

    private final RestTemplate restTemplate;

    @Value("${message.provider.url}")
    private String messageProviderURL;
    @Value("${translator.url:${TRANSLATOR_URL:http://localhost:7070/translate}}")
    private String translatorURL;


    public GreetingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

/*

    @GetMapping("/greeting")
    public Greeting getGreeting(@RequestParam(value="name", defaultValue="World") String name) {
        String url = UriComponentsBuilder.fromHttpUrl(messageProviderURL)
                .queryParam("name", name)
                .toUriString();
        Message message = restTemplate.getForObject(url, Message.class);
        return new Greeting(message.getMessage());
    }

    @PostMapping("/translate")
    public Greeting translateGreeting(@RequestParam Greeting greeting) {
        String url = UriComponentsBuilder.fromHttpUrl(messageProviderURL)
                .toUriString();
        return restTemplate.postForObject(url, greeting, Greeting.class);
    }
*/

    @GetMapping("/greeting")
    public Greeting getGreeting(
            @RequestParam(defaultValue = "World") String name,
            @RequestParam(required = false) String lang) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(messageProviderURL)
                .queryParam("name", name);

        if (lang != null && !lang.isBlank()) {
            builder.queryParam("lang", lang);
        }

        String url = builder.toUriString();
        Message message = restTemplate.getForObject(url, Message.class);

        return new Greeting(message.getMessage());
    }
}

    class Message {
    private long id;
    private String message;

    public Message(long id, String content) {
        this.id = id;
        this.message = content;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

