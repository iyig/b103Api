package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get10 extends GoRestBaseUrl {

// https://gorest.co.in/public/v1/users/128529
@Test
    public void get10(){
    //SEt the URL
    spec.pathParams("first","users","second",128529);

    // sEt the expected data

    GoRestTestData obj= new GoRestTestData();
    Map<String,String> dataMap=obj.dataMapMethod("Anamika Joshi", "anamika_joshi@corkery-ritchie.info","male","actice");

Map<String,Object>expectedData=obj.expectedDataMapMethod(null,dataMap);
    System.out.println(expectedData);


    // Send the request and get the response

   Response response= given().spec(spec).get("/{first}/{second}");
response.prettyPrint();
}
}