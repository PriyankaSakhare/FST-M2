package activities;

import static io.restassured.RestAssured.given;


//import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Activity1 {
	// Declare request specification
    RequestSpecification requestSpec;
    // Declare response specification
    ResponseSpecification responseSpec;
    String sshkey="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQCqFkkckMh4Caek04spI1b0Ut9hcA+cmvej4k1XT9vHciRkrUveBB3TdtPSTsk+Kk3Sohml/d1JllZP/tPWus0osqBbewrKg9/9GNtp3t44ySrvXtrGt8GqedT3DdaoK9kdbycA7i2j5+Xs1HQltIh0WdDVGct65NBg9ZPh4IrU8uz6vb36zkUaJZDcuQGkZKlZrh6J3a4Tl4Z22xG5EB06rYCrQ6Bbp7fg9+CSLKxlJhLsQLnCH315KN3MW/ScRUPlpaKJH6Eswmo+fbe8b2Lx1oYsLpti65W8KdCIMKJCFWEU3CSX2QY8Q7RnMD0WOas6iBhGOfj6hNKLU9NUsm1e0PDcZ0narm/+z5dxc1T/UY5m99Pou5hUgziQ0ZGwoH6BwHNhyygT9Nx4jCeSqR4ZQUtGQ9EELexEwDTN0x/hV1KwvJKcdjefsxktM0Y1I+o4LPt74xp1qnVIBdqcH4ouaVOoYX1X0/wsxr2oT+mTiq9cNZ8Cl3LwDqQ91iw5hsc= gmx\\04558m744@LAPTOP-K7HJCDF0";
    int keyid;
    
    @BeforeClass
    public void setUp() {
        // Create request specification
        requestSpec = new RequestSpecBuilder()
                // Set content type
                .setContentType(ContentType.JSON)
                //Set Authorization  Header
                .addHeader("Authorization", "token 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")
                // Set base URL
                .setBaseUri("https://api.github.com")
                // Build request specification
                .build();
 
        responseSpec = new ResponseSpecBuilder()
                // Check status code in response
                .expectStatusCode(201)
                // Check response content type
                .expectContentType("application/json")
                // Check if response contains name property
                //.expectBody("id", equalTo("alive"))
                // Build response specification
                .build();
    }
    
  @Test(priority=1)
  public void addToken() {

	  String reqBody = 
			  "{\"title\": \"testAPIKey\", \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQCqFkkckMh4Caek04spI1b0Ut9hcA+cmvej4k1XT9vHciRkrUveBB3TdtPSTsk+Kk3Sohml/d1JllZP/tPWus0osqBbewrKg9/9GNtp3t44ySrvXtrGt8GqedT3DdaoK9kdbycA7i2j5+Xs1HQltIh0WdDVGct65NBg9ZPh4IrU8uz6vb36zkUaJZDcuQGkZKlZrh6J3a4Tl4Z22xG5EB06rYCrQ6Bbp7fg9+CSLKxlJhLsQLnCH315KN3MW/ScRUPlpaKJH6Eswmo+fbe8b2Lx1oYsLpti65W8KdCIMKJCFWEU3CSX2QY8Q7RnMD0WOas6iBhGOfj6hNKLU9NUsm1e0PDcZ0narm/+z5dxc1T/UY5m99Pou5hUgziQ0ZGwoH6BwHNhyygT9Nx4jCeSqR4ZQUtGQ9EELexEwDTN0x/hV1KwvJKcdjefsxktM0Y1I+o4LPt74xp1qnVIBdqcH4ouaVOoYX1X0/wsxr2oT+mTiq9cNZ8Cl3LwDqQ91iw5hsc= gmx\04558m744@LAPTOP-K7HJCDF0\"}";
      Response response = given().spec(requestSpec)// Use requestSpec
              .body(reqBody) // Send request body
              .when().post("https://api.github.com/user/keys"); // Send POST request

      // Assertions
      response.then().log().all().spec(responseSpec); // Use responseSpec
      JsonPath jsonPathEvaluator = response.jsonPath();
      keyid =jsonPathEvaluator.get("id");
      System.out.println("Your ssh key id is " + keyid);
     }
  @Test(priority=2)
  public void getkey() {
      
      Response response = 
    	        given().spec(requestSpec) // Set headers
    	        // Add query parameter
    	        .when().get("https://api.github.com/user/keys"); // Send GET request
   // Print response and assertion  
      		response.then().log().all().statusCode(200);
  }
 
  @Test(priority=3)
  public void deleteKey() {
      Response response = given().spec(requestSpec) // Use requestSpec
    		  .pathParam("id",keyid)
              .when().delete("https://api.github.com/user/keys/{id}"); // Send Delete request

      // Print response and assertion  
		response.then().log().all().statusCode(204);
  }
  
  
}
