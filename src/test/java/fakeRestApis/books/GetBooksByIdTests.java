package fakeRestApis.books;

import api.dto.BookDTO;
import helpers.PropertiesManager;
import helpers.SerializationHelper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * API tests for the
 * <a href="https://fakerestapi.azurewebsites.net/">FakeRestApi</a>
 * - GET /api/v1/Books/{id}
 */
@Slf4j
public class GetBooksByIdTests {

    private final BookDTO createdBookDTO = BookDTO.getDefaultWithRandomId();

    @BeforeClass
    public void setup() {
        PropertiesManager.loadProperties();
        RestAssured.baseURI = PropertiesManager.getProperty("fakeRestApisBaseUrl");
    }

    @Test // Treating it as bug here: Description and Excerpt fields are changing each time a new request with a
    // specific id is performed
    public void getBooksById_alreadyExistingId_expect_responseStatusCode200() {
        // Arrange
        BookDTO alreadyCreatedBookDTO = BookDTO.getAlreadyInsertedBookDTO();

        // Get the book by its ID and verify its details
        Response response = given()
                .when()
                .get("/api/v1/Books/" + alreadyCreatedBookDTO.getId())
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .extract()
                .response();

        // Assert
        String jsonResponse = response.asString();
        BookDTO bookDTO = SerializationHelper.deSerializeJsonToDto(jsonResponse, BookDTO.class);
        assertEquals(alreadyCreatedBookDTO.getTitle(), bookDTO.getTitle());
        assertEquals(alreadyCreatedBookDTO.getDescription(), bookDTO.getDescription()); // Treating it as bug here: Description changes each time a request is done
        assertEquals(alreadyCreatedBookDTO.getPageCount(), bookDTO.getPageCount());
        assertEquals(alreadyCreatedBookDTO.getExcerpt(), bookDTO.getExcerpt()); // Treating it as bug here: Excerpt changes each time a request is done
        assertEquals(alreadyCreatedBookDTO.getDateTime(), bookDTO.getDateTime());
    }

    @Test
    public void getBooksById_nonExistingId_expect_responseStatusCode404NotFound() {
        given()
                .when()
                    .get("/api/v1/Books/" + createdBookDTO.getId() + "1")
                .then()
                    .statusCode(404);
    }

    @Test
    public void getBooksById_invalidId_expect_responseStatusCode400BadRequest() {
        given()
                .when()
                .get("/api/v1/Books/" + "aaa")
                .then()
                .statusCode(400);
    }

}
