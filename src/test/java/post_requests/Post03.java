package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.junit.Test;
import posjos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post03 extends JsonPlaceHolderBaseUrl {

/*
Given
https://jsonplaceholder.typicode.com/todos
{
"userId":55,
"title":"Tidy your room"
"completed": false
}
When
I send POST Reqeust to the Url
Then
Status code is 201
And
response body is like{
                       "userId":55,
                       "title":"Tidy your room"
                        "completed": false
 */

@Test

public void post03(){

    // set the Url

    spec.pathParam("first","todos");

    // Set the expected data

      JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(55,"Tidy your room",false);
    System.out.println(expectedData);
    //Send the request and get the response
  Response response=  given().spec(spec).body(expectedData).post("{first}");
  response.prettyPrint();

//Do Assertion
             JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);
    System.out.println("actualData" + actualData);


    assertEquals(201,response.statusCode());
    assertEquals(expectedData.getTitle(),actualData.getTitle());
    assertEquals(expectedData.getUserId(),actualData.getUserId());
    assertEquals(expectedData.getCompleted(),actualData.getCompleted());

}


}
