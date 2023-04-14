package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.DummyRestApiDataPojo;
import pojo.DummyRestApiResponseBodyPojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {


    /*
    URL:// https://dummy.restapiexample.com/api/v1/update/21

     "employee_name": "Ali Can",
       "employee_salary": 320800,
       "employee_age": 61,
       "profile_image": ""
       }
    Test Case:Type by using Gherkin Language
    Assert:
    i) Status code is 200
    ii) Response body should be like the following

                              {
                          "status": "success",
                         "data": {
                             "employee_name": "Ali Can",
                             "employee_salary": 320800,
                              "employee_age": 61,
                               "profile_image": ""
                             },
                              "message": "Successfully! Record has been fetched."
                              }

     */
    @Test
    public void put02() {
        spec.pathParams("first", "update", "second", 21);

        //Set the expected data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Ali Can", 111111, 23, "Perfect image");
        System.out.println("expectedData = " + expectedData);
        DummyRestApiResponseBodyPojo expectedBodyPojo=new DummyRestApiResponseBodyPojo("success",expectedData,"Successfully! Record has been fetched.");
      //expectedBodyPojo objesini actualData data ile karşılaştırarak assertion yapmak icin oluşturduk
        //Send the request and get the response

        Response response = given().spec(spec).when().body(expectedData).put("/{first}/{second}");
        response.prettyPrint();


        //Do Assetion
DummyRestApiResponseBodyPojo actualData=ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiResponseBodyPojo.class);
assertEquals(200,response.statusCode());
assertEquals(expectedBodyPojo.getStatus(),actualData.getStatus());
assertEquals(expectedBodyPojo.getMessage(),actualData.getMessage());

assertEquals(expectedData.getEmployee_name(),actualData.getData().getEmployee_name());
assertEquals(expectedData.getEmployee_salary(),actualData.getData().getEmployee_salary());
assertEquals(expectedData.getEmployee_age(),actualData.getData().getEmployee_age());
assertEquals(expectedData.getProfile_image(),actualData.getData().getProfile_image());






    }
}
