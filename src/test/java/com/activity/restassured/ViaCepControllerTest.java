package com.activity.restassured;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;

import io.restassured.RestAssured;

public class ViaCepControllerTest {
    
    @BeforeAll
    public static void setBaseURI() {
        RestAssured.baseURI = "https://viacep.com.br";
        get("/ws/02362070/json").then().statusCode(200);
    }

    @Test
    public void testZipCodeSuccess() {
        get("/ws/02362070/json").then().assertThat().statusCode(200);
    }

    @Test
    public void testContentZipCodeSuccess() {
        String bairro = get("/ws/02362070/json").then().assertThat().statusCode(200).extract().path("bairro");
        assertEquals("Jardim Campo Limpo (Zona Norte)", bairro);
    }

    @Test
    public void testBadResquestZipCodeFailure() {
        get("/ws/02362070a/json").then().assertThat().statusCode(400);
    }
}
