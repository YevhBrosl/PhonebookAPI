package com.phonebook.restAssured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic2ViYUB5aC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcyMTM5OTkxNiwiaWF0IjoxNzIwNzk5OTE2fQ.swB_8wZlWfHN7psklTIDWvhhl9K7BVlzi-7lwFjXDqs";

    public static final String AUTH = "Authorization";

    @BeforeMethod
    public void init() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }
}
