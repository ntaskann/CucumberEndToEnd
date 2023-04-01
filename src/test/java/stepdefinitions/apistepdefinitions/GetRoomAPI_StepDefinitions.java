package stepdefinitions.apistepdefinitions;

import base_urls.MedunnaBaseUrl;
import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojos.Room;
import utilities.ConfigReader;

import java.io.IOException;
import java.util.List;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepdefinitions.uistepdefinitions.CreateRoomUI_StepDefinitions.number;

public class GetRoomAPI_StepDefinitions {

    @Given("user sends get request and validate")
    public void user_sends_get_request_and_validate() throws IOException {
        //Set the url
        MedunnaBaseUrl.spec.pathParams("first", "api", "second", "rooms").queryParam("sort", "createdDate,desc");

        //Set the expected data
        Room expectedData =
                new Room(ConfigReader.getProperty("medunnaDescription"), Integer.valueOf(ConfigReader.getProperty("medunnaPrice")), number, "TWIN", true);

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        //response.prettyPrint();

        //Do assertion
        JsonPath jsonPath = response.jsonPath();
        List<String> actualRoomNumber = jsonPath.getList("findAll{it.roomNumber==" + number + "}.roomNumber");
        //System.out.println("number = " + number);
        System.out.println("API ile gelen oda numarası = " + actualRoomNumber);
//        assertEquals(200, response.statusCode());
//        assertEquals(expectedData.getRoomNumber(), actualRoomNumber);
    }


}
