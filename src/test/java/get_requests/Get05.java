package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {
/*
Given
https://restful-booker.herokuapp.com/booking
When
User send a GET request to the URL
Then
status code is 299
And
Among the data there should be someone whose firstname is "Aaron" and last name is "Chené (Data
icerisne firstname değeri "Sally", lastname değer "Brown" olan biri olmalı)
 */
@Test
    public void get05(){
//SEt the URl
   spec.pathParam("first","booking").
        queryParams("firstname","Sally","lastname","Brown");

   // Set the expected data

    // Send the request
   Response response = given().spec(spec).when().get("/{first}");
   response.prettyPrint();


  // Do Assertion
   response.then().statusCode(200); //Status code is 200

    //mong the data there should be someone whose firstname is "Aaron" and last name is "Chené (Data
    //icerisne firstname değeri "Sally", lastname değer "Brown" olan biri olmalı)
    assertTrue(response.asString().contains("bookingid"));
}

}
