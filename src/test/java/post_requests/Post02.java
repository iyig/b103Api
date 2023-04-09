package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerOkuAppBaseUrl {
 /*

      Given
       https://restful-booker.herokuapp.com/booking
       { "firstname": "JOhn",
                  "lastname": "Doe",
                  "totalprice": 1111,
                  "depositpaid": true,
                  "bookingdates": {
                      "checkin": "2021-09-09",
                      "checkout": "2021-09-21"
                   }
    }
    gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
    }
      "bookingİd": 5315,
       "booking": {
           "firstname": "John",
           "lastname": "Doe",
           "totalprice": ,1111
           "depositpaid": true,
           "bookingdates": {
                               "checkin": "2021-09-09",
                                "checkout": "2021-09-21"
           }

*/
@Test
    public void post02(){
    spec.pathParam("first","booking");

    // Set the expected data
    HerOkuAppTestData obj =new HerOkuAppTestData();
  Map<String,String> bookingdatesMap=  obj.bookingdatesMapMethod("2021-09-09","2021-09-21");
         Map<String,Object>expectedData= obj.expectedDataMethod("John","Doe",11111,true,bookingdatesMap,null);
    System.out.println("expectedData=" + expectedData);
    //Send the request and get the response

    Response response =given().spec(spec).body(expectedData).post("{first}");
    response.prettyPrint();

    // Do Assertion

  Map<String,Object>actualData=  response.as(HashMap.class);
    System.out.println("actualData="  +actualData);


    assertEquals(200,response.statusCode());
    assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
    assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
    assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
    assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));
    assertEquals(bookingdatesMap.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
    assertEquals(bookingdatesMap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
}

}
