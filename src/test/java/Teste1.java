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

    @Test
    public void testeSegundo() {
        String url = "http://localhost:8080/api/primeiraApiV1";
        String palavra = "teste";

        RestAssured.given()
                .queryParam("palavra", palavra)
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(palavra));
    }

    @Test
    public void testeTerceiro() {
        String pathParam = "Testando";
        String url = "http://localhost:8080/api/primeiraApiV2/" + pathParam;

        RestAssured.given()
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString(pathParam));
    }

    @Test
    public void testeQuarto() {
        String url = "http://localhost:8080/exercicios/parOuImpar";
        String numero = "98";

        RestAssured.given()
                .queryParam("numero", numero)
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString("O numero " + numero + " é par"));
        //.body(Matchers.equalToIgnoringCase())
    }

    @Test
    public void testeQuinto() {
        String pathParam = "1500";
        String url = "http://localhost:8080/exercicios/calculaSalario/" + pathParam;

        RestAssured.given()
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString("Salario bruto = 1500"));
    }

    @Test
    public void testeSexto() {
        String url = "http://localhost:8080/exercicios/validarCpf";
        String numero = "53919663969";

        RestAssured.given()
                .queryParam("cpf", numero)
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.equalTo("CPF Valido"));
    }
}
