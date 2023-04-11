package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.BookingDatesPojo;
import pojo.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12 extends HerOkuAppBaseUrl {
  /*
    https://restful-booker.herokuapp.com/booking/535
    { "firstname": "JOhn",
            "lastname": "Smith",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
        "checkin": "2018-09-21",
                "checkout": "2019-12-21"
    },
        "additionalneeds": "Breakfast"

*/

@Test
    public void get12(){
    // Set the Url
    spec.pathParams("first","booking","second", 535);

   // Set the expected ddta
    BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2018-01-01","2019-01-01");
    BookingPojo expectedData=new BookingPojo("Josh","Allen",111,true,bookingDatesPojo,"super bowl");
    System.out.println("expectedData = " + expectedData);
// Send the request an get the respnse

    Response response=given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();

    //Do Assetion
    BookingPojo actualData=response.as(BookingPojo.class);
    System.out.println("actualData = " + actualData);

    assertEquals(200,response.statusCode());
    assertEquals(expectedData.getFirstname(),actualData.getFirstname());
    assertEquals(expectedData.getLastname(),actualData.getLastname());
    assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
    assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
    assertEquals(bookingDatesPojo.getChecking(),actualData.getBookingdates().getChecking());
    assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());


}
}

