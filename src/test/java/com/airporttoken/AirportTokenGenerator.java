package com.airporttoken;

import com.payloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

public class AirportTokenGenerator {


    @Test
    public void generateToken() {

        // 1. Generated the token for authentication purposes
        String credentials = "email=k.abdivasiev@gmail.com&password=2385121786";
        RestAssured.baseURI = "https://airportgap.com/api";
        Response response = RestAssured.given().header("Content-Type", "application/x-www-form-urlencoded")
                .body(credentials)
                .when()
                .post("/tokens")
                .then().statusCode(200)
                .extract()
                .response();

        // 2. Deserialized response using TypeRef
        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });


        // 3. Storing the generated token in a string
        String token = (String) parsedResponse.get("token");

        // Consider our next request requires a token to authenticate the user
        // 4. Using this generated token in our POST request to get the distance between 2 airports
        String distanceURI = "https://airportgap.com/api/airports/distance";
        Response res = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON).
                header("token", token)
                .body(Payloads.getDistancePayload("ORD", "SEA"))
                .when()
                .post(distanceURI)
                .then()
                .statusCode(200)
                .extract().response();
        System.out.println(res.prettyPrint());

    }

}
