package com.innowise.covidquarkusapi;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
public class CovidResourceTest {

    @Test
    void countriesCount() {
        RestAssured.given()
            .when().get("/covid/countries")
            .then()
            .statusCode(200)
            .body("size()", Matchers.is(248));
    }

    @Test
    void getMinMaxCases() {
        RestAssured.given()
            .pathParam("country", "belarus")
            .queryParam("from", "2020-04-01")
            .queryParam("to", "2020-04-05")
            .get("/covid/country/{country}")
            .then()
            .statusCode(200)
            .body("minCases", Matchers.is(47))
            .body("maxCases", Matchers.is(141));
    }

    @Test
    void getMinMaxCasesForCountryWithProvinces() {
        RestAssured.given()
            .pathParam("country", "united-kingdom")
            .queryParam("from", "2020-04-01")
            .queryParam("to", "2020-04-05")
            .get("/covid/country/{country}")
            .then()
            .statusCode(200)
            .body("minCases", Matchers.is(3594))
            .body("maxCases", Matchers.is(4915));
    }

    @Test
    void unknownCountry() {
        RestAssured.given()
            .pathParam("country", "unknown-country")
            .queryParam("from", "2020-04-01")
            .queryParam("to", "2020-04-05")
            .get("/covid/country/{country}")
            .then()
            .statusCode(404);
    }
}
