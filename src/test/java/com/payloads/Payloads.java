package com.payloads;

public class Payloads {


    public static String getPetPayload(int id, String name) {

        return "{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"status\": \"Always barking, and playing with kids\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 2233,\n" +
                "    \"name\": \"Rex\"\n" +
                "  },\n" +
                "  \"photoUrls\": [\n" +
                "    \"some dog images\",\n" +
                "    \"another images\",\n" +
                "    \"one images\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 555,\n" +
                "      \"name\": \"Roxy\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

    }

    public static String getDistancePayload(String from, String to) {
        return "{\n" +
                "  \"from\": \"" + from + "\",\n" +
                "  \"to\": \"" + to + "\"\n" +
                "}";
    }


}
