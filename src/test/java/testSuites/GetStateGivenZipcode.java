package testSuites;


import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetStateGivenZipcode {
    @Test
    public void requestUsZipCode_checkPlaceNameOnTheResponse_expectedBeverlyHills() {
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().assertThat().body("places[0].'place name'", equalTo("Beverly Hills"));
    }
    @Test
    public void requestUsZipCode90210_checkNumberOfPlaceNamesInResponseBody_expectOne() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }
    @Test
    public void requestUsZipCode90210_checkListOfPlaceNamesInResponseBody_expectContainsBeverlyHills() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", not(hasItem("Beverly Hills")));
    }
    @Test
    public void requestUsZipCode90210_checkStateNameInResponseBody_expectCalifornia() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].state", equalTo("California"));
    }
    @Test
    public void requestUsZipCode90210_checkStatus200_expectTrue() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                statusCode(200);
    }
    @Test
    public void requestUsZipCode90210_checkContentType_expectJson() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                contentType(ContentType.JSON);
                //contentType("application/json");
                //contentType(ContentType.XML);
    }
    @Test
    public void requestUsZipCode90210_logResponseAndRequestDetails() {
        given().
                when().
                log().all().
                get("http://zippopotam.us/us/90210").
                then().
                log().body();
    }
}
