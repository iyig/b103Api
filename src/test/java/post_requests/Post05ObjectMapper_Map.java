package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Map extends JsonPlaceHolderBaseUrl {

/*

https://jsonplaceholder.typicode.com/todos
{
"userId":55,
"title":"Tidy your room",
"completedd":false
}

                  {
                 "userId":55,
                  "title":"Tidy your room",
                  "completedd":false
                  "id":201
}

 */
@Test

    public void post5() throws IOException {
  //Set the URL

    spec.pathParam("first","todos");
    // Set the expected data
   Map<String,Object> expectedData= new JsonPlaceHolderTestData().expectedDataMethod(55,"Tidy your room", false);
       //Send the request snd get the response
          Response response=given().spec(spec).body(expectedData).post("{first}");
         response.prettyPrint();

   // Do Assertion

    Map<String,Object> actualData=new ObjectMapper().readValue(response.asString(), HashMap.class);
    System.out.println("actualData = " + actualData);


    assertEquals(201,response.getStatusCode());
    assertEquals(expectedData.get("userId"), actualData.get("userId"));
    assertEquals(expectedData.get("title"), actualData.get("title"));
    assertEquals(expectedData.get("completed"), actualData.get("completed"));


}



}
