package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {

/*
   http://jsonplaceholder.typicode.com/todos
   1) Status kodunun 200,
   2) 190’dan büyük tüm id'leri ekrana yazdırın ve 10 tane assert edin
   3) 5’dan küçük tüm yaşları ekrana yazdırın ve 4 olanin id si 5 kücük olan 4 tane userId olduğunu doğrulayınbu yaşların içerisinde en büyük yaşın 23 olduğunu
   4) id 5 kücük olanları yazdırın
      ve bunların içerisinde "delectus aut autem" olduğunu test edin
   */
@Test
    public void get07(){


    //Set the URL
    spec.pathParam("first","todos");

    // Set the expected data



    // Send the request and get the response

  Response response=  given().spec(spec).get("/{first}");
    //response.prettyPrint();

    //Do Assertion

    assertEquals(200,response.statusCode());

    //190’dan büyük tüm id'leri ekrana yazdırın ve 10 tane assert edin
         JsonPath jsonPath=response.jsonPath();
         List<Object> list=jsonPath.getList("findAll{it.id>190}.id");// Groovy Java temelli bir programlama dilidir
         System.out.println(list);

    // Assert that there are 10 ids greater than 190 == 10 tan id nin 190 dan büyük olduğunu doğrulayın
    assertEquals(10,list.size());

    // id si 5 den kücük olan tüm userId lerini konsolu yazdırı
    List<Integer>userIdList=jsonPath.getList("findAll{it.id<5}.userId");
    System.out.println("userIdList = " + userIdList);


    //id si 5 den kücük olan 4 tane userId olduğunu doğrulan
     assertEquals(4,userIdList.size());

//id si 5 den kücük olan tüm baslıkları yazdırın "
   List<String>titleList= jsonPath.getList("findAll{it.id<5}.title");
    System.out.println("titleList= " + titleList);
// id si 5 den kücük olan dataların birinin delectus aut autem olduğu dığrulayın
    assertTrue("title uyuşmadı",titleList.contains("delectus aut autemX"));
}
}
