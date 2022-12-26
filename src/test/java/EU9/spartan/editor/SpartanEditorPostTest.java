package EU9.spartan.editor;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.assertj.core.api.HamcrestCondition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import utilities.SpartanUtil;
import net.serenitybdd.rest.SerenityRest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.LinkedHashMap;
import java.util.Map;
import static net.serenitybdd.rest.SerenityRest.given;

@Disabled
@SerenityTest
public class SpartanEditorPostTest {


    @DisplayName ("Editor should be able to POST")
    @Test
    public void postSpartanAsEditor(){
 Map<String, Object> bodyMap = SpartanUtil.getRandomSpartanMap();


        System.out.println("bodyMap = " + bodyMap);

        // send a post request as an editor
        given()
                .auth().basic("editor", "editor")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .log().body()
        .when()
                .post("/api/spartans")
                .then()
                .log().all();

        Ensure.that("Status Code is 201: ", x -> x.statusCode(201));
        Ensure.that("Content Type is JSON: ", vR -> vR.contentType(ContentType.JSON));
        Ensure.that("Success message is correct: ", thenPart -> thenPart.body("success", is("A Spartan is Born!") ));
        Ensure.that("id is not null: ", thenPart -> thenPart.body("data.id", notNullValue() ));
        Ensure.that("Name is correct: ", nameCheck -> nameCheck.body("data.name", is(bodyMap.get("name"))));
        Ensure.that("gender is correct: ", genderCheck -> genderCheck.body("data.gender", is(bodyMap.get("gender"))));
        Ensure.that("phone is correct: ", phoneCheck -> phoneCheck.body("data.phone", is(bodyMap.get("phone"))));
        // get id and save in a variable (id)
        String id = SerenityRest.lastResponse().jsonPath().getString("data.id");
        Ensure.that("check location header ends with latest generated ID: ", headerCheck -> headerCheck.header("Location", endsWith(id)));
        /*
        ensure that
        status code is 201
        content type is JSON
        success message is A Spartan is Born!
        id is not null
        name is correct
        gender is correct
        phone is correct

        check location header ends with newly generated ID number
         */

    }
    /*
    We can give a unique name to each execution using name =""
    and if we want to get index of iteration we can use {index}
    and also if you want to adde the dynamic parameter into the test name
    {0}, {1}, {2} ---> based on the order you provide as argument the data from that indexed item
     */

    @ParameterizedTest(name = "New Spartan {index} - name : {0}")
    @CsvFileSource(resources="/SpartanData.csv",numLinesToSkip = 1)
    public void postSpartanWithCSV (String name, String gender, long phone){
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        Map <String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("gender", gender);
        bodyMap.put("phone", phone);

        System.out.println("bodyMap = " + bodyMap);
    }

}
