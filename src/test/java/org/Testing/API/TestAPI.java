package org.Testing.API;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class TestAPI {

    @Test
    public void test_positive() {
        RestAssured
                .given().queryParam("2")
                .when().get("https://reqres.in/api/login")
                .then().log().all()
                .and().assertThat().statusCode(200)
                .and().assertThat().header("Content-Type", "application/json; charset=utf-8")
                .and().assertThat().body("data", Matchers.notNullValue());
    }

    @Test
    public void test_negative() {
        RestAssured
                .given().contentType("application/json")
                .when().post("https://reqres.in/api/login")
                .then().log().all()
                .and().assertThat().statusCode(400)
                .and().assertThat().header("Content-Type", "application/json; charset=utf-8")
                .and().assertThat().body("error", Matchers.equalTo("Missing email or username"));
    }

}    