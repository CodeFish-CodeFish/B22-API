package com.post;

import com.payloads.Payloads;
import com.pojos.airportPojo.AttributePojo;
import com.pojos.airportPojo.DataPojo;
import com.pojos.airportPojo.DistancePojo;
import com.pojos.airportPojo.FromAirport;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetDistanceAirPorts {

    @Test
    public void getDistance() {


        //RestAssured.baseURI = "https://airportgap.com";
        //RestAssured.basePath = "api/airports/distance";

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"from\": \"ORD\",\n" +
                        "    \"to\": \"SEA\"\n" +
                        "}")
                .when()
                .post("https://airportgap.com/api/airports/distance")
                .then()
                .statusCode(200)
                .extract()
                .response();

        DistancePojo parsedResponse = response.as(DistancePojo.class);

//        System.out.println(parsedResponse.getData().getId());
//        System.out.println(parsedResponse.getData().getType());
//
//        System.out.println(parsedResponse.getData().getAttributes().getKilometers());
//        System.out.println(parsedResponse.getData().getAttributes().getMiles());
//        System.out.println(parsedResponse.getData().getAttributes().getNautical_miles());
//
//        System.out.println(parsedResponse.getData().getAttributes().getFrom_airport().getLongitude());

        DataPojo dataPojo = parsedResponse.getData();
        AttributePojo attributePojo = dataPojo.getAttributes();
        FromAirport fromAirport = attributePojo.getFrom_airport();
        String longitude = fromAirport.getLongitude();

        System.out.println(longitude);
        System.out.println(attributePojo.getKilometers());

        System.out.println(attributePojo.getTo_airport().getCity());
        System.out.println(attributePojo.getTo_airport().getName());
    }


    @Test
    public void getDistanceWithPayload(){

        /*
        construct your call and get a distance from Atlanta Airport to JFK using re-usable method payload
         */
        int count = 0;
       try {
           for (int i = 0; i < 1000; i++) {
               count++;
               RestAssured.given()
                       .accept(ContentType.JSON)
                       .contentType(ContentType.JSON)
                       .body(Payloads.getDistancePayload("ATL", "JFK"))
                       .when()
                       .post("https://airportgap.com/api/airports/distance")
                       .then()
                       .statusCode(200);
           }

       }catch (AssertionError e){
           System.out.println(count);
           System.out.println(e.getMessage());
       }


    }


}
