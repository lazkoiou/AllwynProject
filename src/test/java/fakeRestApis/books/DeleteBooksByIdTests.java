package fakeRestApis.books;

import api.dto.BookDTO;
import helpers.PropertiesManager;
import helpers.SerializationHelper;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * API tests for the
 * <a href="https://fakerestapi.azurewebsites.net/">FakeRestApi</a>
 * - DELETE /api/v1/Books
 */
@Slf4j
public class DeleteBooksByIdTests {

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

    @Test // Treating it as a bug here: Fails as entries are not saved properly
    public void deleteBookById_expect_bookDeleted() {
        // Create a new book entry
        BookDTO createdBookDTO = BookDTO.getDefaultWithRandomId();
        given()
                .header("Content-Type", "application/json")
                .body(SerializationHelper.serializeDtoToJson(createdBookDTO))
                .when()
                .post("/api/v1/Books")
                .then()
                .statusCode(200);

        // Delete the new book entry
        given()
                .header("Content-Type", "application/json")
                .body(SerializationHelper.serializeDtoToJson(createdBookDTO))
                .when()
                .delete("/api/v1/Books" + createdBookDTO.getId())
                .then()
                .statusCode(200);

        // Get the book by its ID and verify that it has been deleted
        given()
                .when()
                    .get("/api/v1/Books/" + createdBookDTO.getId())
                .then()
                    .statusCode(404);
    }

    @Test // Treating it as a bug here: Fails as we get 200 response for deleting entries that do not exist
    public void deleteBooksById_nonExistentId_expect_404NotFound() {
        BookDTO deletedBookDTO = BookDTO.getEditedBookDTO();
        deletedBookDTO.setId(201); // non existent id

        // Create a new book entry
        given()
                .header("Content-Type", "application/json")
                .body(SerializationHelper.serializeDtoToJson(deletedBookDTO))
                .when()
                .delete("/api/v1/Books" + deletedBookDTO.getId())
                .then()
                .statusCode(404);
    }

}
