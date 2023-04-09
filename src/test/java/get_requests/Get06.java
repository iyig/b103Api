package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {
/*
Given
https://restful-booker.herokuapp.com/booking/23
When
User send o GET request to the URL

Http Status Code should be 200
And
Response content type is "applicaiton/json"
And
Response body should be like

{
"firstname":"Josh",
"lastname":"Allen",
"totalprice": 111;
"depositpaid":true
"bookingdates":{
"checkin": "2018-01-01",
"chekcout": "2019-01-01"
},
"additionalneeds": "None"
}
 */
@Test
    public void get06(){
   // Set the URL
   spec.pathParams("first","booking","second",23);

   //Send the request and get the response

    Response response=given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();
    // Do Assertion
   // 1.YOl
    response.then().statusCode(200).contentType(ContentType.JSON).
            body("firstname",equalTo("Josh"),"lastname",equalTo("Allen"),
                    "totalprice",equalTo(111),"depositpaid",equalTo(true),
                    "bookingdates.checkin",equalTo("2018-01-01"),
                    "bookingdates.checkout",equalTo("2019-01-01"),
                    "additionalneeds",equalTo("super bowls")
            );

    // 2.yol
    JsonPath jsonPath=response.jsonPath();
   assertEquals("Josh",jsonPath.getString("firstname"));
    assertEquals("Allen",jsonPath.getString("lastname"));
    assertEquals(111,jsonPath.getInt("totalprice"));
    assertTrue("true",jsonPath.getBoolean("depositpaid"));
    assertEquals("2018-01-01",jsonPath.getString("bookingdates.checkin"));
    assertEquals("2019-01-01",jsonPath.getString("bookingdates.checkout"));
    assertEquals("super bowls",jsonPath.getString("additionalneeds"));


    // 3.YOl Test NG Soft Assert

    SoftAssert softAssert =new SoftAssert();
    // Assertion
    softAssert.assertEquals(jsonPath.getString("firstname"),"John","firstname uyusmadı");
    softAssert.assertEquals(jsonPath.getString("lasttname"),"Smith","lastname uyusmadı");
    softAssert.assertEquals(jsonPath.getInt("totalprice"),111,"totalprice uyusmadı");
    softAssert.assertTrue(jsonPath.getBoolean("depositpaid"),"depositpaid uyusmadı");
    softAssert.assertEquals(jsonPath.getString("bookingdates"),"2018-01-01","bookingdates uyusmadı");
    softAssert.assertEquals(jsonPath.getString("bookingdates"),"2019-01-01","bookingdates uyusmadı");
    softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast","additionalneeds uyusmadı");
    //3.softAssert.assertAll() diyerek doğrulamayı kontrol et. Aksi taktirde test hep "Pass" olur
    softAssert.assertAll();
}
}
