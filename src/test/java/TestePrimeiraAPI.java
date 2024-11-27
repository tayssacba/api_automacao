import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class TestePrimeiraAPI {

    String url = "http://localhost:8080/api";
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(url).build();

    @Test
    public void testePrimeiro() {
        String path = "/primeiraApi";

        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(containsString("sucesso"));
    }

    @Test
    public void testeSegundo() {
        String path = "/primeiraApiV1";
        String palavra = "teste";

        given()
                .spec(requestSpecification)
                .queryParam("palavra", palavra)
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(containsString(palavra));
    }

    @Test
    public void testeTerceiro() {
        String pathParam = "Testando";
        String path = "/primeiraApiV2/" + pathParam;

        given()
                .spec(requestSpecification)
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(containsString(pathParam));
    }
}
