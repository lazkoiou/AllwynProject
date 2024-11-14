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
 * - POST /api/v1/Books
 */
@Slf4j
public class PostBooksTests {

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

    @Test // Treating it as a bug here: Fails as new books are do not correctly added in the list
    public void addNewBook_getBooksById_expect_addedInList() {
        BookDTO createdBookDTO = BookDTO.getDefaultWithRandomId();

        // Create a new book entry
        given()
                .header("Content-Type", "application/json")
                .body(SerializationHelper.serializeDtoToJson(createdBookDTO))
                .when()
                .post("/api/v1/Books")
                .then()
                .statusCode(200);
        bookDTOsToDelete.add(createdBookDTO);

        // Get the book by its ID and verify its details
        Response response = given()
                .when()
                    .get("/api/v1/Books/" + createdBookDTO.getId())
                .then()
                    .statusCode(200)
                    .body("size()", greaterThan(1))
                    .extract()
                    .response();

        // Assert
        String jsonResponse = response.asString();
        BookDTO bookDTO = SerializationHelper.deSerializeJsonToDto(jsonResponse, BookDTO.class);
        assertEquals(createdBookDTO.getTitle(), bookDTO.getTitle());
        assertEquals(createdBookDTO.getDescription(), bookDTO.getDescription());
        assertEquals(createdBookDTO.getPageCount(), bookDTO.getPageCount());
        assertEquals(createdBookDTO.getExcerpt(), bookDTO.getExcerpt());
        assertEquals(createdBookDTO.getDateTime(), bookDTO.getDateTime());
    }

    @Test // Treating it as a bug here: If the API is designed to prevent similar ids, then
    // 409 Conflict should appear when we try to add a new book with the same id
    // In case we allowed to have similar ids it would be ok, but it is an issue I would raise
    // and discuss it with the Product Owner and/or the Developer
    public void addNewBook_alreadyExistingId_expect_response409Conflict() {
        // Create a new book entry with the same id as an existing one
        given()
                .header("Content-Type", "application/json")
                .body(SerializationHelper.serializeDtoToJson(BookDTO.getAlreadyInsertedBookDTO()))
                .when()
                .post("/api/v1/Books")
                .then()
                .statusCode(409);
    }

    @Test
    public void addNewBook_nullableFieldsAreNull_expect_response200() {
        BookDTO bookDTO = BookDTO.getDefaultWithRandomId();
        bookDTO.setTitle(null);
        bookDTO.setDescription(null);
        bookDTO.setExcerpt(null);

        // Create a new book entry with null id
        given()
                .header("Content-Type", "application/json")
                .body(SerializationHelper.serializeDtoToJson(bookDTO))
                .when()
                .post("/api/v1/Books")
                .then()
                .statusCode(200);

        // We could do a GET here and assert that the book returned has values null in these fields
        // but for the sake of this project there is no need to
    }

}
