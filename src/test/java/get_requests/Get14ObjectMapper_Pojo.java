package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.JsonPlaceHolderPojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {

/*
Given
https.//jsonplaceholder.typicode.com/todos/198
When
I send GET request to the URL
Then
Status code is 200
And response body is like      {
                               "userId":10,
                               "id":198,
                              "title":"quis eius est sint explicaba"
                                 "completed":true
                                   }



 */

@Test
    public void get14(){

    //Set the expected data
    spec.pathParams("first","todos","second",198);

    //set the expected data
   JsonPlaceHolderPojo expectedData= new JsonPlaceHolderPojo(10,"quis eius est sint explicaba",true);
    System.out.println(expectedData);

    //Send the request and get the response
Response response=given().spec(spec).when().get("/{first}/{second}");
response.prettyPrint();

// Do Assertion
   JsonPlaceHolderPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
    System.out.println(actualData);

    assertEquals(200,response.statusCode());
    assertEquals(expectedData.getUserId(),actualData.getUserId());
    assertEquals(expectedData.getTitle(),actualData.getTitle());
    assertEquals(expectedData.getCompleted(),actualData.getCompleted());

}

}
