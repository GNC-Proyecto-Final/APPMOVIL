package com.example.utec.gncpfinal.servicios;

public class RestClient {

     private static String baseUrl = "http://10.0.2.2:8180/";

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        RestClient.baseUrl = baseUrl;
    }
}
