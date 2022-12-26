package utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {
    public static ResponseSpecification responseSpec;
    public static RequestSpecification adminSpec;
    public static RequestSpecification userSpec;

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we don't need to type  http metadata each time
        baseURI = "http://3.83.225.50";
        port = 7000;
        //basePath="/api";

        adminSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all();

        userSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("user", "user")
                .log().all();

        responseSpec =
                expect().statusCode(200)
                        .and()
                        .contentType(ContentType.JSON)
                        .logDetail(LogDetail.ALL);
    }

    @AfterAll
    public static void close() {
        //reset the information we set above. useful when dealing with multiple API addresses
        reset();
    }

}
