import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

public class Teste1 {

    @Test
    public void testePrimeiro() {
        String url = "http://localhost:8080/api/primeiraApi";

        RestAssured.given()
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString("sucesso"));
    }
}
