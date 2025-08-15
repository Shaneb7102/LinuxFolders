package greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${message.provider.url}")
    private String messageProviderURL;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println("URL is " +messageProviderURL + name);
        Message message = restTemplate.getForObject(messageProviderURL+name, Message.class);
        return new Greeting(message.getMessage());
    }
}
