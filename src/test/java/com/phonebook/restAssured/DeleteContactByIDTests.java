package com.phonebook.restAssured;

import com.phonebook.dto.ContactDto;
import com.phonebook.dto.ErrorDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactByIDTests extends TestBase {

    String id;

    @BeforeMethod
    public void precondition() {
        ContactDto contactDto = ContactDto.builder()
                .name("Greg")
                .lastName("House")
                .email("house@gm.com")
                .phone("123456789012")
                .address("New Jersey")
                .description("doctor")
                .build();

        String message = given()
                .header(AUTH, TOKEN)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .extract().path("message");

        String[] split = message.split(": ");
        id = split[1];
    }

    @Test
    public void deleteContactByIdSuccessTest() {
//        String message =
        given()
                .header(AUTH, TOKEN)
                .when()
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(200)
//                .assertThat().body(containsString("Contact was deleted!"));
                .assertThat().body("message", equalTo("Contact was deleted!"));
//                .extract().path("message");
    }

    @Test
    public void deleteContactByWrongIDErrorTest() {
//        ErrorDto errorDto =
        given()
                .header(AUTH, TOKEN)
                .when()
                .delete("contacts/d214993a-835d-4723-8a88-9ed407deea8")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message", containsString("not found in your contacts!"));
//                .extract().response().as(ErrorDto.class);


    }
}
