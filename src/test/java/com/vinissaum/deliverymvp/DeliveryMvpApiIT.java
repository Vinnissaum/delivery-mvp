package com.vinissaum.deliverymvp;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import com.vinissaum.deliverymvp.domain.model.Kitchen;
import com.vinissaum.deliverymvp.domain.repositories.KitchenRepository;
import com.vinissaum.deliverymvp.utils.ClearDatabase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@ClearDatabase
public class DeliveryMvpApiIT {

    @LocalServerPort
    private int port;

    @Autowired
    private KitchenRepository repository;

    private static final Long NON_EXISTENT_KITCHEN_ID = 999L;

    private final List<Kitchen> kitchenData = new ArrayList<>();

    private final Kitchen AmericanKitchen = new Kitchen();

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.basePath = "/kitchens";
        RestAssured.port = port;
        prepareData();
    }

    @Test
    public void shouldReturn200Status_WhenSearchKitchens() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturn201Status_WhenInsertKitchen() {
        given()
            .body("{ \"name\": \"Test Kitchen\" }")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .when()
            .post()
            .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldHaveNumberOfPreparedData_WhenGetAllRegister() {
        given()
            .accept(ContentType.JSON)
            .when()
            .get()
            .then()
            .body("", hasSize(kitchenData.size()));
    }

    @Test
    public void shouldReturn200Status_WhenSearchKitchenById() {
        given()
            .pathParam("kitchenId", AmericanKitchen.getId())
            .accept(ContentType.JSON)
            .when()
            .get("/{kitchenId}")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", equalTo(AmericanKitchen.getName()));
    }

    @Test
    public void shouldReturn404Status_WhenSearchNotExistingKitchen() {
        given()
            .pathParam("kitchenId", NON_EXISTENT_KITCHEN_ID)
            .accept(ContentType.JSON)
            .when()
            .get("/{kitchenId}")
            .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }


    public void prepareData() {
        AmericanKitchen.setName("American");
        Kitchen kitchen = new Kitchen();
        kitchen.setName("Indian");
        kitchenData.addAll(List.of(AmericanKitchen, kitchen));
        repository.saveAll(kitchenData);
    }

}
