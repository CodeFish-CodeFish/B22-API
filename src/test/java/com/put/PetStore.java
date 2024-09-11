package com.put;

import com.payloads.Payloads;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class PetStore {

    @Test
    public void updatePet(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath ="v2/pet/";

        // 1. Create a pet

        // 2. Get the pet

        // 3. Update the pet

        // 4. Get updated pet

        // 5. Delete updated pet


    }

}
