package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import posjos.JsonPlaceHolderPojo;
import util.ObjectMapperUtils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
/*
Given
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
    public void post05() throws IOException {
    //Set the URL
spec.pathParam("first","todos");

    JsonPlaceHolderPojo expectedData=new JsonPlaceHolderPojo(55,"Tidy your room",false);
//Send the request and the reponse

   Response response= given().spec(spec).when().body(expectedData).post("{first}");
    response.prettyPrint();

   // Do Assertion


    JsonPlaceHolderPojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
    System.out.println("actualData = " + actualData);

    assertEquals(201,response.statusCode());
    assertEquals(expectedData.getUserId(),actualData.getUserId());
    assertEquals(expectedData.getTitle(),actualData.getTitle());
    assertEquals(expectedData.getCompleted(),actualData.getCompleted());

}
}
