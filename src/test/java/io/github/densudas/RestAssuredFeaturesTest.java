package io.github.densudas;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.github.densudas.models.Post;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RestAssuredFeaturesTest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    void getAllPosts() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(100));
    }

    @Test
    void getSinglePost() {
        given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("title", notNullValue())
                .body("body", notNullValue());
    }

    @Test
    void createPost() {
        String requestBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }

    @Test
    void updatePost() {
        String requestBody = "{\"id\": 1, \"title\": \"foo updated\", \"body\": \"bar updated\", \"userId\": 1}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("title", equalTo("foo updated"))
                .body("body", equalTo("bar updated"));
    }

    @Test
    void deletePost() {
        given()
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }

    @Test
    void filterPostsByUser() {
        given()
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("$.userId", everyItem(equalTo(1)));
    }

    @Test
    void extractResponseData() {
        Response response = given()
                .when()
                .get("/posts/1")
                .then()
                .extract().response();

        int id = response.path("id");
        String title = response.path("title");

        assertEquals(1, id);
        assertEquals("sunt aut facere repellat provident occaecati excepturi optio reprehenderit", title);
    }

    @Test
    void logRequestAndResponse() {
        given()
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .log().all()
                .statusCode(200);
    }

    /**
     * Example of serialization with Rest Assured.
     * This test demonstrates how to:
     * 1. Create a Java object (Post)
     * 2. Automatically serialize it to JSON using Rest Assured
     * 3. Send it in a POST request
     * 4. Validate the response
     */
    @Test
    void serializeAndCreatePost() {
        // Create a Post object to be serialized
        Post post = new Post(null, "Serialization Example", "This post was created using serialization", 1);

        // Rest Assured will automatically serialize the Post object to JSON
        given()
                .contentType(ContentType.JSON)
                .body(post) // Object is automatically serialized to JSON
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", equalTo("Serialization Example"))
                .body("body", equalTo("This post was created using serialization"))
                .body("userId", equalTo(1));
    }

    /**
     * Example of deserialization with Rest Assured.
     * This test demonstrates how to:
     * 1. Send a GET request
     * 2. Deserialize the JSON response to a Java object (Post)
     * 3. Access the object's properties
     */
    @Test
    void deserializePostResponse() {
        // Send request and deserialize the response to a Post object
        Post post = given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .extract()
                .as(Post.class); // Deserialize JSON to Post object

        // Validate the deserialized object
        assertNotNull(post);
        assertEquals(1, post.getId());
        assertEquals(1, post.getUserId());
        assertNotNull(post.getTitle());
        assertNotNull(post.getBody());
        
        // Print the deserialized object
        System.out.println("Deserialized Post: " + post);
    }

    /**
     * Example of serialization and deserialization with collections.
     * This test demonstrates how to:
     * 1. Deserialize a JSON array response to a List of Java objects
     * 2. Create a List of Java objects and serialize it to JSON
     */
    @Test
    void workWithCollections() {
        // Deserialize JSON array to List of Post objects
        List<Post> posts = Arrays.asList(
                given()
                        .when()
                        .get("/posts")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Post[].class) // Deserialize JSON array to Post array
        );

        // Validate the deserialized collection
        assertNotNull(posts);
        assertEquals(100, posts.size());
        
        // Print the first few posts
        System.out.println("First 3 posts:");
        posts.stream().limit(3).forEach(System.out::println);
        
        // Create a list of Post objects to serialize
        List<Post> newPosts = Arrays.asList(
                new Post(null, "First Post", "Content of first post", 1),
                new Post(null, "Second Post", "Content of second post", 1)
        );
        
        // Serialize the list and send it (this is just an example - the API doesn't support batch creation)
        // In a real scenario, you might use a different endpoint that accepts an array of objects
        given()
                .contentType(ContentType.JSON)
                .body(newPosts) // List is automatically serialized to JSON array
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201); // The API actually returns 201 for a single post, but this is just for demonstration
    }
}
