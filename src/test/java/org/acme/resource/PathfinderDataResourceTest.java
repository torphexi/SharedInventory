package org.acme.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
public class PathfinderDataResourceTest {

    @Test
    public void testGetAllItemNames() {
        given()
            .when().get("/pathfinder-data/names")
            .then()
            .statusCode(200)
            .body("$", hasSize(greaterThan(0))); // Verify we get a non-empty list
    }

    @Test
    public void testGetSpecificItem() {
        given()
            .when().get("/pathfinder-data/item/abadars-flawless-scale")
            .then()
            .statusCode(200)
            .body("name", is("abadars-flawless-scale"));
    }
}
