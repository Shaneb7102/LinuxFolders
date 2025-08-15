package translator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

    /*
        this is a tricky ahh map

        logic wise, i guess just a stringbuilder?

         Do we also need a message class in translator?


        Producer endpoint returns a Message
        Consumer takes that Message and converts it to a Greeting
        maybe to harmonise things we can have this returning a Message too,
            then doing the conversion in Consumer again

            the translator  is like an inbetween for the greeting and the message provider ig?
            so like we could just have the message class also witin tanslate so we can build the right string,



            create a message of type message, and then return it?

            yeah that seems reasonable

            maybe the consumer doesn't attach the greeting then? they just send the language
            and the translator handles everything and sends the whole message

            could be better just to rewrite the original consumer getmessage request.
                default language of english,
                 but if they supply a language then it goes to translator?



            were looking at mike drawing on the whiteboard on the issue

            SO mike suggests, from browser, if the url is the standard request for message, greeting goes straight to message, grabs it and returns to greeting to display
            But, check for additional args, so instead of /greeting, maybe it could be /greeting/translated?ger, then it would go straight to translation endpoint,
            then that return would be based on what language is specified in the url

            that makes sense to me
            rewriting it to use query params
            like

            GET /message?name=Bob&lang=fr -> {"id":42,"message":"Bonjour, Bob!"}

            so the producer will now be forwarding to /translate if lang param is present


        This is my request in MessageController
        so just passes in a message string and lang

    So if a lang param is there, it just skips from message controller to translator controller?

    yes, it bounces the message to translator controller, then gets the response, then passes the response back to greetingcontroller


    So the translator should just route the tranlated message back through message service?

    i think that ensures separation of concerns

    okay John software design over here

    ah no youre right

    i invented spring boot

    wait so do we need to check for language params here then?
    or just have the path be translate


    nah, the check is already done in MessageController. if it's not there, it just returns the base message

    so get mapping can just be "translate"

    seems like it yes
    alright
    
    Was i oka to remove the requestmapping url rom above translatecontroller
    since the only url would be for the getmapping method


       // Translate and return translated message
        return restTemplate.getForObject(
                UriComponentsBuilder.fromHttpUrl(translatorURL)
                        .queryParam("text", baseMessage.getMessage())
                        .queryParam("lang", lang)
                        .toUriString(),
                Message.class
        );
     */


    @RestController

    public class TranslateController {

        private static final Map<String, Map<String, String>> DICT = createDict();

        private static Map<String, Map<String, String>> createDict() {
            Map<String, Map<String, String>> dict = new HashMap<>();

            Map<String, String> hello = new HashMap<>();
            hello.put("en", "Hello");
            hello.put("es", "Hola");
            hello.put("fr", "Bonjour");
            dict.put("Hello", hello);

            Map<String, String> helloWorld = new HashMap<>();
            helloWorld.put("en", "Hello, World");
            helloWorld.put("es", "Hola, Mundo");
            helloWorld.put("fr", "Bonjour, le monde");
            dict.put("Hello, World", helloWorld);

            return dict;
        }

        @GetMapping("/translate")
        public Translation translate(
                @PathVariable("lang") String lang,
                @RequestParam("text") String text)
        {
            String translated = text;
            Map<String, String> langMap = DICT.get(text);
            if (langMap != null && langMap.get(lang) != null) {
                translated = langMap.get(lang);
            }
            return new Translation(translated);
        }

}