package com.phonebook.okhttp;

import com.google.gson.Gson;
import com.phonebook.dto.ContactDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddContactOkhttpTests {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic2ViYUB5aC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcyMTMyMDM2MiwiaWF0IjoxNzIwNzIwMzYyfQ.YMf8YG9VMU1UfeG7uGgdbX5bQcwqo-TlwJbBIRpvhC0";

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");

    @Test
    public void addContactOkhttpTest() throws IOException {

        ContactDto contactDto = ContactDto.builder()
                .name("John")
                .lastName("Doe")
                .email("john.doe@gm.com")
                .phone("1234567890")
                .address("Berlin")
                .description("friend")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(contactDto), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .header("Authorization", token)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        ContactDto dto = gson.fromJson(responseBody, ContactDto.class);

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(200, response.code());

    }
}
