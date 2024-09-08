package com.get;

import com.pojos.petPojo.PetPojo;
import com.pojos.petPojo.Tags;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class PetSore {

    @Test
    public void getDog(){

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/2000";

        Response response = RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(200).extract().response();

        PetPojo parsedResponse = response.as(PetPojo.class);

        int dogId = parsedResponse.getId();
        String dogName = parsedResponse.getName();
        String dogStatus = parsedResponse.getStatus();

        String categoryName = parsedResponse.getCategory().getName();

        System.out.println(categoryName);

        List<String> photoUrls = parsedResponse.getPhotoUrls();

        System.out.println(photoUrls);

        List<Tags> tags = parsedResponse.getTags();

        for (int i = 0; i < tags.size(); i++) {
//            System.out.println(tags.get(i).getId());
//            System.out.println(tags.get(i).getName());
            if (tags.get(i).getName().equals("Sharik")){
                System.out.println(tags.get(i).getName());
                System.out.println(tags.get(i).getId());
            }
        }
    }

}
