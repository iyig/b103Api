package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {

/*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body'İ PUT ettiğinizde
    {
    "userId": 21,
    "title": "Wash the dishes",
    "completed": false
    }
    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    {
    "userId": 21,
    "title": "Wash the dishes",
    "completed": false,
    "id": 198
    }
    */
@Test

    public void put01(){
    spec.pathParams("first","todos","second", 198);

    //Set the expected data
    Map<String,Object> expectedData=new HashMap<>();
    expectedData.put("userId",21);
    expectedData.put("title","Wash the dishes");
    expectedData.put("completed",false);
    System.out.println(expectedData);
    //Send the request and get the response
    Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).put("/{first}/{second}");
  response.prettyPrint();
  //Do Assertion

    Map<String,Object> actualData=response.as(HashMap.class);
    System.out.println("actualData=" + actualData);

    assertEquals(200,response.statusCode());
    assertEquals(expectedData.get("completed"),actualData.get("completed"));
    assertEquals(expectedData.get("title"),actualData.get("title"));
    assertEquals(expectedData.get("userId"),actualData.get("userId"));

}
    @Test//Dinamik Yöntem

    public void put01b(){
        spec.pathParams("first","todos","second", 198);

        //Set the expected data
        JsonPlaceHolderTestData obj= new JsonPlaceHolderTestData();
        Map<String,Object> expectedData=obj.expectedDataMethod(21, "Wash the dishes",false);
        System.out.println(expectedData);

        //Send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).put("/{first}/{second}");
        response.prettyPrint();
        //Do Assertion

        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println("actualData=" + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

    }

}


