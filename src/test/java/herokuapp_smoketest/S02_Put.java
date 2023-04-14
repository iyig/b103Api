package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.BookingDatesPojo;
import pojo.BookingPojo;

import static herokuapp_smoketest.S01_Post.bookingId;
import static io.restassured.RestAssured.given;
import static util.AuthenticationHerOkuApp.generateToken;

public class S02_Put extends HerOkuAppBaseUrl {

/*

tps://restful-booker.herokuapp.com/booking/{id}

          {
            "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
    },
        "additionalneeds": "Breakfast"

When
Send Put request
Then
Status code should be 200
And

   {
            "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
    },
        "additionalneeds": "Breakfast"

 */
@Test
    public void put01(){
    System.out.println("bookingId = " + bookingId);
    spec.pathParams("first","booking","second",bookingId);


    //Set the expected data

    BookingDatesPojo bookingDatesPojo=new BookingDatesPojo("2018-01-01","2019-01-01");
    BookingPojo expectedData=new BookingPojo("Ali","Can",111 ,true,bookingDatesPojo,"Breakfast");
    System.out.println("expectedData = " + expectedData);

    //Send the reqeust and get the response
   Response response= given().spec(spec).
            header("Cookie","token="+generateToken()).
            body(expectedData).put("/{first}/{second}");

   response.prettyPrint();
}

}
