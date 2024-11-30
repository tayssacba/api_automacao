import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class TesteFutebolAPI {

    @Test
    public void exercicioApi_Futebol() {

        String url = "https://api.api-futebol.com.br/v1/campeonatos";


        RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer live_369a3475ba039356ce2add7815da12")
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body(Matchers.containsString("Campeonato Brasileiro 2024"));
    }

    @Test
    public void exemploExtracaoDeInformacoesJsonPath() {

        String url = "https://api.api-futebol.com.br/v1/campeonatos/10/tabela";


        String primeiroColocado = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer live_369a3475ba039356ce2add7815da12")
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("[0].time.nome_popular");

        System.out.println("O Primeiro colocado é: " + primeiroColocado);
    }

    @Test
    public void exemploExtracaoDeInformacoesUtilizandoResponseJsonPath() {

        Response response;

        String url = "https://api.api-futebol.com.br/v1/campeonatos/10/tabela";


        response = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer live_369a3475ba039356ce2add7815da12")
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        String primeiroColocado, segundoColocado, terceiroColocado, quartoColocado;

        primeiroColocado = response.path("[0].time.nome_popular");
        segundoColocado = response.path("[1].time.nome_popular");
        terceiroColocado = response.path("[2].time.nome_popular");
        quartoColocado = response.path("[3].time.nome_popular");

        System.out.println("O Primeiro colocado é: " + primeiroColocado);
        System.out.println("O Segundo colocado é: " + segundoColocado);
        System.out.println("O Terceiro colocado é: " + terceiroColocado);
        System.out.println("O Quarto colocado é: " + quartoColocado);
    }

    @Test
    public void exercicioTabelaCampeonato01E02() {

        Response response;
        String url = "https://api.api-futebol.com.br/v1/campeonatos/10/tabela";

        response = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer live_369a3475ba039356ce2add7815da12")
                .when()
                .get(url)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        for (int i = 0; i < 20; i++) {
            System.out.println((i + 1) + "º - " + response.path("[" + i + "].time.nome_popular"));
        }
        int primeiroColocado = response.path("[0].pontos");
        int ultimoColocado = response.path("[19].pontos");

        Assert.assertTrue("Primeiro colocado não tem mais pontos que o ultimo", primeiroColocado >= ultimoColocado);
    }
}