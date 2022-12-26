package EU9.spartan.admin;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SerenityTest
public class SpartanAdminGetTest {

    @BeforeAll
    public static void init(){
        baseURI = "http://3.83.225.50:7000";

    }

    @Test
    public void getAllSpartan(){
        SerenityRest.given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
        .when()
                .get("/api/spartans")
        .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);

        // if you send a request using SerenityRest, the response object can be
        // obtained from the lastResponse() method without needing a instantiate and
        // initilaze a response object
        System.out.println("statusCode = " + lastResponse().statusCode());
        System.out.println("SerenityRest.lastResponse().path(\"id\") = " + lastResponse().path("id"));

        System.out.println("lastResponse().jsonPath().getString(\"name\") = " + lastResponse().jsonPath().getString("name"));
    }


    @DisplayName("Get Assertions with Serenity")
    @Test
    public void assertWithSerenity(){
        SerenityRest.given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}");

    // Serenity way of assertion
        Ensure.that("Status code is 200", validatableResponse -> validatableResponse.statusCode(200));
        Ensure.that("Content Type is JSON", vLRes -> vLRes.contentType(ContentType.JSON));
        Ensure.that("ID is 15", vLRes -> vLRes.body("id", is (15)));
    }

    //

}
