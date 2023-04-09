package patch_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {

/*
Given
https://jsonplaceholder.typicode.com/todos/198
{
"title": "Wash the dishes"
}
When
  I send PATCH Request to the Url
  Then
  status code is 200
  And response body ise like {
                                userId":10,
                                "title": "Wash the dishes
                                "completed": true,
                                "id": 198
                                }
 */

@Test
    public void patch01(){
    //Set the Url
    spec.pathParams("first","todos","second",198);
    // Set the expected data
    JsonPlaceHolderTestData obj=new JsonPlaceHolderTestData();
    Map<String,Object> expectedData=obj.expectedDataMethod(null, "Wash the dishes", null);
    expectedData.put("title","Wash the dishes");
    System.out.println(expectedData);


    //Send the request and get the response

   Response response= given().spec(spec).body(expectedData).contentType(ContentType.JSON).patch("/{first}/{second}");
   response.prettyPrint();

   //Do Assertion
    Map<String,Object>actualData=response.as(HashMap.class);
    System.out.println(actualData);

    assertEquals(200, response.statusCode());
    assertEquals(expectedData.get("title"), actualData.get("title"));




}

}
