package message;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MessageResourceTest {

    @Test
    public void testPlainMessageEndpoint() {
        given()
          .when().get("/message")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }


    @Test
    public void testNameMessageEndpoint() {
        String uuid = UUID.randomUUID().toString();
        given()
                .pathParam("name", uuid)
                .when().get("/message/{name}")
                .then()
                .statusCode(200)
                .body(is("hello " + uuid + "\n"));
    }

}