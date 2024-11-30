import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class ExemploPetStore {

    @Test
    public void adicinarNovoUsuario() {
        String bodyUser = "[{\n" +
                "    \"id\": 123,\n" +
                "    \"username\": \"QA Academy\",\n" +
                "    \"firstName\": \"QA\",\n" +
                "    \"lastName\": \"Academy\",\n" +
                "    \"email\": \"contato@qaacademy.com.br\",\n" +
                "    \"password\": \"123456\",\n" +
                "    \"phone\": \"11977413512\",\n" +
                "    \"userStatus\": 1\n" +
                "  }]";

        String url = "https://petstore.swagger.io/v2/user/createWithList";

        RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(bodyUser)
                .when()
                .post(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}