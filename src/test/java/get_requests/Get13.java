package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import posjos.GoRestDataPojo;
import posjos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13 extends GoRestBaseUrl {
    /*
    https://gorest.co.in/public/v1/users/247158

    {
        "meta":null,
        "data":{
        "id":2508,
        "name":"Chandak Deshpande VM",
        "email":"deshpande_sharmila_vm@becker.name",
        "gender": "female",
        "status":"inactive"
    }
*/

@Test

    public void get13(){
spec.pathParams("first","users","second",247152);
    // Set the expected data
    GoRestDataPojo goRestDataPojo=new GoRestDataPojo("Chandak Dutta","dutta_chandak@bartoletti.name","female","inactive");
    GoRestPojo expectedData=new GoRestPojo(null,goRestDataPojo);
    System.out.println("expectedData = " + expectedData);

    // Send the request an get the respnse

  Response response=  given().spec(spec).when().get("/{first}/{second}");
    response.prettyPrint();


    //Do assertion
    GoRestPojo actualData=response.as(GoRestPojo.class);
assertEquals(200,response.statusCode());
assertEquals(goRestDataPojo.getName(), actualData.getData().getName());
assertEquals(goRestDataPojo.getEmail(), actualData.getData().getEmail());
assertEquals(goRestDataPojo.getGender(), actualData.getData().getGender());
assertEquals(goRestDataPojo.getStatus(), actualData.getData().getStatus());
assertEquals(expectedData.getMeta(), actualData.getMeta());
;


}
}
