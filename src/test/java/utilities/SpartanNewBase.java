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
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http metadada
        baseURI = "http://44.195.19.167";
        port =7000;

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
    public static void close(){
        //reset the infor we set above. useful when dealing with multiple API addresses
        reset();
    }

}
