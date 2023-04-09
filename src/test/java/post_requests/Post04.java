package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.IExpectedExceptionsHolder;
import posjos.BookingDatesPojo;
import posjos.BookingPojo;
import posjos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04 extends HerOkuAppBaseUrl{

 /*
       https://restful-booker.herokuapp.com/booking
       { "firstname": "Ali",
                  "lastname": "Can",
                  "totalprice": 999,
                  "depositpaid": true,
                  "bookingdates": {
                      "checkin": "2021-09-21",
                      "checkout": "2021-12-21"
                   },
                   "additionalneeds": "Breakfast"
    }
    gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
    }
       "booking": {
           "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 999,
           "depositpaid": true,
           "bookingdates": {
                               "checkin": "2021-09-21",
                                "checkout": "2021-12-21"
           },
           "additionalneeds": "Breakfast"
*/
@Test
    public void post04(){
    //Set the URL
    spec.pathParam("first" ,"booking");

    //Set the expected data

   BookingDatesPojo bookingDatesPojo= new BookingDatesPojo( "2021-09-21" ,"2021-12-21");
  BookingPojo expectedData= new BookingPojo("Ali","Cam",999,true,bookingDatesPojo,"Breakfast");
    System.out.println("expectedData" + expectedData);
//Send the  request  and get the response
Response response=given().spec(spec).when().body(expectedData).post("{first}");
response.prettyPrint();

//Do Assertion
    BookingResponsePojo actualData=response.as(BookingResponsePojo.class);
    System.out.println("actualData" + actualData);
assertEquals(200,response.statusCode());
assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
assertEquals(bookingDatesPojo.getChecking(),actualData.getBooking().getBookingdates().getChecking());
assertEquals(bookingDatesPojo.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());
}

}
