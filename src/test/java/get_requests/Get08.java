package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonPlaceHolderBaseUrl {
/*
Given
    https://jsonplaceholder.typidode.com/todos/2
    When
    I send Get request to the URL
    Then
    Status code is 200
    And "copleted" is false
    And "userId"is "
    And "title" is "quis ut nam facilis et officia quai"
    And header "Via" is "1.1 vegur"
    And heder "Server" is "cloudflare"
    {
    "userId": 1,
    "id":2,
    "title": "quis ut nam facilis et officia qui",
    "completed":false
*/
@Test
    public void get08(){
   // Set the URL
    spec.pathParams("first","todos","second",2);

    // Set the expected data:neden karşıdan geln Data yı map'e çevirmenizi isteyecekler ve sizin oluşturduğunuz map ile

    JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
    Map<String,Object> expectedData=obj.expectedDataMethod(1,"quis ut nam facilis et officia quai",false);
   expectedData.put("id",2);
   expectedData.put("Via","1.1 vegur");
   expectedData.put("Server","cloudflare");
    System.out.println(expectedData);

    // Send the request and get thr response
    Response response=given().spec(spec).get("/{first}/{second}");
    response.prettyPrint();

    // Do Assertion
Map<String,Object>actualData=response.as(HashMap.class);
assertEquals(200,response.statusCode());
assertEquals(expectedData.get("completed"),actualData.get("completed"));
    assertEquals(expectedData.get("title"),actualData.get("title"));
    assertEquals(expectedData.get("userId"),actualData.get("userId"));
assertEquals(expectedData.get("id"),actualData.get("id"));
    // And header 1.1

assertEquals(expectedData.get("Via"),response.getHeader("Via"));
  //And header server is clouflare

    assertEquals(expectedData.get("Server"),response.getHeader("Server"));




}
}

