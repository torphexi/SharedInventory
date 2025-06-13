package org.acme.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import org.acme.model.PathfinderItem;
import org.acme.service.PathfinderDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class PathfinderDataResourceTest {

    @InjectMock
    PathfinderDataService pathfinderDataService;
    
    private PathfinderItem createTestItem(String name) {
        PathfinderItem item = new PathfinderItem();
        item.setName(name);
        item.setId(name.toLowerCase().replace("'", "").replace(" ", "-")); // Set ID to match normalized name
        return item;
    }

    @BeforeEach
    void setup() {
        // Create test data that all tests will use
        List<PathfinderItem> mockItems = Arrays.asList(
            createTestItem("Abadar's Flawless Scale"),
            createTestItem("Test Item")
        );
        Mockito.when(pathfinderDataService.loadAllJsonFiles()).thenReturn(mockItems);
        Mockito.when(pathfinderDataService.findItemByName("abadars-flawless-scale"))
               .thenReturn(Optional.of(createTestItem("Abadar's Flawless Scale")));
    }

    @Test
    public void testGetAllItemNames() {
        given()
            .when().get("/pathfinder-data/names")
            .then()
            .statusCode(200)
            .body("$", hasSize(2)); // Verify we get exactly our two mocked items
    }

    @Test
    public void testGetSpecificItem() {
        given()
            .when().get("/pathfinder-data/item/abadars-flawless-scale")
            .then()
            .statusCode(200)
            .body("name", is("abadars-flawless-scale"));
    }

    @Test
    public void testSpecificNameExists() {
        given()
            .when().get("/pathfinder-data/names")
            .then()
            .statusCode(200)
            .body("$", hasItem("abadars-flawless-scale")); // Check for a specific item we know exists
    }

    @Test
    public void testNameEndpointReturnsServiceData() {
        given()
            .when().get("/pathfinder-data/names")
            .then()
            .statusCode(200)
            .body("$", hasSize(2))
            .body("$", hasItem("test-item")); // Verify normalized form of "Test Item"
    }
}
