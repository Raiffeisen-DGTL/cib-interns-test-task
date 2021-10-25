package ru.raiffeisen.soksapp;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.raiffeisen.soksapp.entity.SearchSockResponse;
import ru.raiffeisen.soksapp.entity.SockEntity;
import ru.raiffeisen.soksapp.repositary.SocksRepository;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SoksappApplicationTests {

    @Autowired
    private SocksRepository socksRepository;


    @BeforeEach
    void setUp() {
        socksRepository.deleteAll();
    }

    @Test
    void testSockIncome() {

        int statusCode = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"1\"" +
                        "}")
                .when()
                .post("/api/socks/income")
                .getStatusCode();

        Assertions.assertThat(statusCode)
                .isEqualTo(200);

        List<SockEntity> allSocks = socksRepository.findAll();

        int newStatus = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"2\"" +
                        "}")
                .when()
                .post("/api/socks/income")
                .getStatusCode();

        SockEntity red = socksRepository.findSocks("red", 100);

        Assertions.assertThat(red).isNotNull();
        Assertions.assertThat(red.getQuantity())
                .isEqualTo(3);
        Assertions.assertThat(red.getColor())
                .isEqualTo("red");
        Assertions.assertThat(red.getCottonPart())
                .isEqualTo(100);
    }

    @Test
    void testThatQuantityBecameLessAfterUpdate() {
        int statusCode = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"5\"" +
                        "}")
                .when()
                .post("/api/socks/income")
                .getStatusCode();

        Assertions.assertThat(statusCode)
                .isEqualTo(200);

        List<SockEntity> allSocks = socksRepository.findAll();
        Assertions.assertThat(allSocks)
                .hasSize(1);

        int deleteStatus = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"3\"" +
                        "}")
                .when()
                .post("/api/socks/outcome")
                .getStatusCode();

        Assertions.assertThat(statusCode)
                .isEqualTo(200);

        SockEntity red = socksRepository.findSocks("red", 100);

        Assertions.assertThat(red).isNotNull();
        Assertions.assertThat(red.getQuantity())
                .isEqualTo(2);

    }

    @Test
    void testThatSocsIsRemovedIfQuntityEqualToZer() {
        int statusCode = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"5\"" +
                        "}")
                .when()
                .post("/api/socks/income")
                .getStatusCode();

        Assertions.assertThat(statusCode)
                .isEqualTo(200);

        List<SockEntity> allSocks = socksRepository.findAll();
        Assertions.assertThat(allSocks)
                .hasSize(1);

        int deleteStatus = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"5\"" +
                        "}")
                .when()
                .post("/api/socks/outcome")
                .getStatusCode();

        Assertions.assertThat(statusCode)
                .isEqualTo(200);

        SockEntity red = socksRepository.findSocks("red", 100);

        Assertions.assertThat(red).isNull();
    }

    @Test
    void testThat400CodeWillBeReturnedIfTooManySocs() {
        int statusCode = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"5\"" +
                        "}")
                .when()
                .post("/api/socks/income")
                .getStatusCode();

        Assertions.assertThat(statusCode)
                .isEqualTo(200);

        List<SockEntity> allSocks = socksRepository.findAll();
        Assertions.assertThat(allSocks)
                .hasSize(1);

        int deleteStatus = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"6\"" +
                        "}")
                .when()
                .post("/api/socks/outcome")
                .getStatusCode();

        Assertions.assertThat(deleteStatus)
                .isEqualTo(400);

    }


    @Test
    void testIfParameterIsNotDefined400CodeWillBeReturned() {
        int statusCode = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"5\"" +
                        "}")
                .when()
                .post("/api/socks/income")
                .getStatusCode();

        Assertions.assertThat(statusCode)
                .isEqualTo(400);
    }

    @Test
    void testSearchByQualsCriteria() {
        int statusCode = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"5\"" +
                        "}")
                .when()
                .post("/api/socks/income")
                .getStatusCode();

        Assertions.assertThat(statusCode)
                .isEqualTo(200);

        SearchSockResponse response = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .queryParam("color", "red")
                .queryParam("operation", "equals")
                .queryParam("cottonPart", "100")
                .get("/api/socks")
                .body()
                .as(SearchSockResponse.class);

        Assertions.assertThat(response.getCount())
                .isEqualTo(5);
    }

    @Test
    void testSearchByMoreThan() {
        int statusCode = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"80\"," +
                        "\"quantity\":\"5\"" +
                        "}")
                .when()
                .post("/api/socks/income")
                .getStatusCode();
        RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .header("Content-type", "application/json")
                .and()
                .body("{" +
                        "\"color\":\"red\"," +
                        "\"cottonPart\":\"100\"," +
                        "\"quantity\":\"5\"" +
                        "}")
                .when()
                .post("/api/socks/income")
                .getStatusCode();

        Assertions.assertThat(statusCode)
                .isEqualTo(200);

        SearchSockResponse response = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .queryParam("color", "red")
                .queryParam("operation", "moreThan")
                .queryParam("cottonPart", "70")
                .get("/api/socks")
                .body()
                .as(SearchSockResponse.class);


        Assertions.assertThat(response.getCount())
                .isEqualTo(10);

    }
	@Test
	void testSearchByLessThan() {

		int statusCode = RestAssured.given()
				.log()
				.all()
				.baseUri("http://localhost:8080")
				.header("Content-type", "application/json")
				.and()
				.body("{" +
						"\"color\":\"red\"," +
						"\"cottonPart\":\"80\"," +
						"\"quantity\":\"5\"" +
						"}")
				.when()
				.post("/api/socks/income")
				.getStatusCode();
		RestAssured.given()
				.log()
				.all()
				.baseUri("http://localhost:8080")
				.header("Content-type", "application/json")
				.and()
				.body("{" +
						"\"color\":\"red\"," +
						"\"cottonPart\":\"70\"," +
						"\"quantity\":\"5\"" +
						"}")
				.when()
				.post("/api/socks/income")
				.getStatusCode();

		Assertions.assertThat(statusCode)
				.isEqualTo(200);

        SearchSockResponse response = RestAssured.given()
				.log()
				.all()
				.baseUri("http://localhost:8080")
				.queryParam("color", "red")
				.queryParam("operation", "lessThan")
				.queryParam("cottonPart", "85")
				.get("/api/socks")
                .as(SearchSockResponse.class);

        Assertions.assertThat(response.getCount())
                .isEqualTo(10);
	}

    @Test
    void incorrectSearchOperation() {

        int statusCode = RestAssured.given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .queryParam("color", "red")
                .queryParam("operation", "sfdsd")
                .queryParam("cottonPart", "85")
                .get("/api/socks")
                .getStatusCode();

        Assertions.assertThat(statusCode)
                .isEqualTo(400);
    }
}
