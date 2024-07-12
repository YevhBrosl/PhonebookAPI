package com.phonebook.httpclient;

import com.google.gson.Gson;
import com.phonebook.dto.AllContactsDto;
import com.phonebook.dto.ContactDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GetAllContactsHttpclientTests {

    Gson gson = new Gson();

    HttpClient client = HttpClient.newHttpClient();

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic2ViYUB5aC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTcyMTMyMDM2MiwiaWF0IjoxNzIwNzIwMzYyfQ.YMf8YG9VMU1UfeG7uGgdbX5bQcwqo-TlwJbBIRpvhC0";


    @Test
    public void getAllContactsHttpclientTest() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://contactapp-telran-backend.herokuapp.com/v1/contacts"))
                .GET()
                .header("Authorization", token)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(200, response.statusCode());

        AllContactsDto contactsDto = gson.fromJson(response.body(), AllContactsDto.class);
        List<ContactDto> contacts = contactsDto.getContacts();

        for (ContactDto c : contacts) {
            System.out.println(c.getId());
            System.out.println(c.getName());
            System.out.println(c.getPhone());
        }
    }


}
