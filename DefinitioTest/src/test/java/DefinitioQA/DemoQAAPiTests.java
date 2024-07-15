package DefinitioQA;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;

public class DemoQAAPiTests {
	@Test
	public void testCreateUser() {
		String requestBody = "{ \"name\": \"John\", \"job\": \"QA Engineer\" }";

		// Send the POST request and capture the response
		Response response = given().header("Content-Type", "application/json").body(requestBody).when()
				.post("https://reqres.in/api/users").then().statusCode(201).body("name", equalTo("John"))
				.body("job", equalTo("QA Engineer")).extract().response();

		// Extract the id from the response
		String userId = response.jsonPath().getString("id");
		System.out.println("Created user ID: " + userId);

		// Optional: Use the extracted ID for further assertions or API calls
		given().pathParam("userId", userId).when().get("https://reqres.in/api/users/{userId}").then().statusCode(200)
				.body("data.id", equalTo(Integer.parseInt(userId)));
	}

	@Test
	public void testGetUsers() {
		Response response = given().when().get("https://reqres.in/api/users?page=1").then().statusCode(200).extract()
				.response();

// Print the list of users
		String users = response.jsonPath().getString("data");
		System.out.println("List of users: " + users);

// Additional assertion to check if page number is correct
		response.then().body("page", equalTo(1));
	}
}
