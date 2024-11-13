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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.testng.AssertJUnit.assertEquals;

/**
 * API tests for the
 * <a href="https://fakerestapi.azurewebsites.net/">FakeRestApi</a>
 * - GET /api/v1/Books/{id}
 */
@Slf4j
public class PutBooksByIdTests {
    private final List<BookDTO> bookDTOsToDelete = new ArrayList<>();
    @BeforeClass
    public void setup() {
        PropertiesManager.loadProperties();
        RestAssured.baseURI = PropertiesManager.getProperty("fakeRestApisBaseUrl");
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        for (BookDTO bookDtoToDelete : bookDTOsToDelete) {
            given()
                    .when()
                    .delete("/api/v1/Books/" + bookDtoToDelete.getId())
                    .then()
                    .statusCode(200);
        }
    }

    @Test // Treating it as a bug here: Fails as new books are not saved correctly
    public void putBookByIdWithEditedFields_expect_fieldsChanged() {
        // Create a new book entry
        BookDTO createdBookDTO = BookDTO.getDefaultWithRandomId();
        given()
                .header("Content-Type", "application/json")
                .body(SerializationHelper.serializeDtoToJson(createdBookDTO))
                .when()
                .post("/api/v1/Books")
                .then()
                .statusCode(200);
        bookDTOsToDelete.add(createdBookDTO); // add the created book to the list to delete it after the test

        // Edit the created book entry
        BookDTO editedBookDTO = BookDTO.getEditedBookDTO();
        editedBookDTO.setId(createdBookDTO.getId()); // set the id to the same as created book to avoid creating a new book
        given()
                .header("Content-Type", "application/json")
                .body(SerializationHelper.serializeDtoToJson(editedBookDTO))
                .when()
                .put("/api/v1/Books" + editedBookDTO.getId())
                .then()
                .statusCode(200);

        // Get the book by its ID and verify its details
        Response response = given()
                .when()
                    .get("/api/v1/Books/" + editedBookDTO.getId())
                .then()
                    .statusCode(200)
                    .body("size()", greaterThan(1))
                    .extract()
                    .response();

        // Assert
        String jsonResponse = response.asString();
        BookDTO bookDTO = SerializationHelper.deSerializeJsonToDto(jsonResponse, BookDTO.class);
        assertEquals(editedBookDTO.getTitle(), bookDTO.getTitle());
        assertEquals(editedBookDTO.getDescription(), bookDTO.getDescription());
        assertEquals(editedBookDTO.getPageCount(), bookDTO.getPageCount());
        assertEquals(editedBookDTO.getExcerpt(), bookDTO.getExcerpt());
        assertEquals(editedBookDTO.getDateTime(), bookDTO.getDateTime());
    }

    @Test // Treating it as a bug here: Fails as we get 200 response for editing entries that do not exist
    public void putBooksById_nonExistentId_expect_404NotFound() {
        BookDTO editedBookDTO = BookDTO.getEditedBookDTO();
        editedBookDTO.setId(201); // non existent id

        given()
                .header("Content-Type", "application/json")
                .body(SerializationHelper.serializeDtoToJson(editedBookDTO))
                .when()
                .put("/api/v1/Books" + editedBookDTO.getId())
                .then()
                .statusCode(404);
    }

}
