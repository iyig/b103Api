package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.BookingDatesPojo;
import pojo.BookingPojo;
import pojo.BookingResponsePojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S01_Post extends HerOkuAppBaseUrl {
/*
Given
https://restful-booker.herokuapp.com/booking

          {
            "firstname": "Jim",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-21",
                "checkout": "2019-01-01"
    },
        "additionalneeds": "Breakfast"

When
send post request
Then
Status Code should be 200
And
   Body should be like

 {
 "bookingid":9268,
 "booking":{

              "firstname": "Jim",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-21",
                "checkout": "2019-01-01"
    },
        "additionalneeds": "Breakfast"

 */


static int bookingId;
@Test
    public void post01(){

    //Set the URL//
    spec.pathParam("first","booking");

    //Set the expected data
    BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2018-01-01","2019-01-01");
    BookingPojo expectedData=new BookingPojo("Jim","Brown",111,true,bookingDatesPojo,"Breakfast");
    System.out.println("expectedData = " + expectedData);

    //send the request and get the response

    Response response=given().spec(spec).when().body(expectedData).post("{first}");
  response.prettyPrint();

  //Do Assertion

   BookingResponsePojo actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),BookingResponsePojo.class);
    System.out.println("actualData = " + actualData);
assertEquals(200,response.statusCode());
assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
assertEquals(expectedData.getBookingdates().getChecking(),actualData.getBooking().getBookingdates().getChecking());
assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());

}

}

