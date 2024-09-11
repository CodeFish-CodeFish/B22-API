package com.put;

import com.payloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;

public class PetStore {

    @Test
    public void updatePet(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath ="v2/pet/";

        // 1. Create a pet
        RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(Payloads.getPetPayload(1989, "Laika"))
                .when()
                .post()
                .then().statusCode(200)
                .extract().response();

        // 2. Get the pet
         RestAssured.given().accept(ContentType.JSON).when()
                 .get("1989")
                 .then()
                 .statusCode(200)
                 .body("id", Matchers.is(1989))
                 .body("name", Matchers.equalTo("Laika"))
                 .extract().response();

        // 3. Update the pet
         String updatedPetName = "Bobik";
         RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON).body(Payloads.getPetPayload(1989, updatedPetName))
                 .when()
                 .put()
                 .then()
                 .statusCode(200)
                 .body("id", Matchers.is(1989))
                 .body("name", Matchers.equalTo(updatedPetName))
                 .extract().response();


        // 4. Get updated pet
        RestAssured.given().accept(ContentType.JSON)
                .when()
                .get("1989")
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(updatedPetName))
                  .extract().response();

        // 5. Delete updated pet
           RestAssured.given().accept(ContentType.JSON).when().delete("1989").then().statusCode(200).extract().response();

    }

}
