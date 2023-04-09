package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {

  /*

   https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
  }
    Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
  {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": 201
   }
*/
/*
De_Serialization:Json datanın Java objesine çevrilmesi
Serialization: Java objesinin Json dataya çevrilmesi
2 türlü De?Serialization yapacağız

İ) Gson:Google tarafından üretilmişitir
İİ) Object Mapper = en popüler
 */

    @Test
    public void post01(){
    //set the URL
    spec.pathParam("first", "todos");
    // set the expected data ==> payload
/*
{
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": 201
   }

 */
// set the expected data==> Payload
    Map<String,Object>expectedData=new HashMap<>();
    expectedData.put("userId",55);
    expectedData.put("title","Tidy your room");
    expectedData.put("completed",false);
    System.out.println("expectedData=" + expectedData);

    //Send the request and get the response
  Response response= given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).post("/{first}");
    response.prettyPrint();
    //Do Assertion

  Map<String, Object>actualData=  response.as(HashMap.class); //DE_Serialization ==> json to java
    System.out.println("actualData=" + actualData);

    assertEquals(201, response.statusCode());
    assertEquals(expectedData.get("completed"),actualData.get("completed"));
    assertEquals(expectedData.get("title"),actualData.get("title"));
    assertEquals(expectedData.get("userId"),actualData.get("userId"));
}

}
