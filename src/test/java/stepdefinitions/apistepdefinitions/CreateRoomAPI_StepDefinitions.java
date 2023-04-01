package stepdefinitions.apistepdefinitions;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import pojos.Room;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;


public class CreateRoomAPI_StepDefinitions {
    Response response;
    int roomNumber = Faker.instance().number().numberBetween(1000, 1000000);
    Room expectedData;

    @Given("user sends post request for room data")
    public void user_sends_post_request_for_room_data() {
        //Set the url
        spec.pathParams("first", "api", "second", "rooms");

        //Set the expected data
        expectedData = new Room("Api'dan yeni oda", 123, roomNumber, "TWIN", true);

        //Send the request and get the response
        response = given(spec).body(expectedData).post("{first}/{second}");
        response.prettyPrint();
    }

    @Then("user gets the room data and assert")
    public void user_gets_the_room_data_and_assert() throws IOException {

        //1.Validation(Matcher)
        assertEquals(201, response.statusCode());
        response.then().body("description", equalTo("Api'dan yeni oda"),
                "roomNumber", equalTo(roomNumber),
                "status", equalTo(true),
                "price", equalTo(123),
                "roomType", equalTo("TWIN")
        );

        //2.Validation(JSonPath)
        JsonPath jsonPath = response.jsonPath();
        assertEquals((int) expectedData.getRoomNumber(), jsonPath.getInt("roomNumber"));
        assertEquals(expectedData.getRoomType(), jsonPath.getString("roomType"));
        assertEquals((int) expectedData.getPrice(), jsonPath.getInt("price"));
        assertEquals(expectedData.getDescription(), jsonPath.getString("description"));
        assertEquals(expectedData.getStatus(), jsonPath.getBoolean("status"));

        //3.Validation(Map)
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.getRoomNumber(), actualData.get("roomNumber"));
        assertEquals(expectedData.getRoomType(), actualData.get("roomType"));
        assertEquals(expectedData.getPrice(), actualData.get("price"));
        assertEquals(expectedData.getDescription(), actualData.get("description"));
        assertEquals(expectedData.getStatus(), actualData.get("status"));

        //4.Validation(Pojo)
        Room actualDataPojo = response.as(Room.class);
        assertEquals(expectedData.getRoomNumber(), actualDataPojo.getRoomNumber());
        assertEquals(expectedData.getRoomType(), actualDataPojo.getRoomType());
        assertEquals(expectedData.getPrice(), actualDataPojo.getPrice());
        assertEquals(expectedData.getDescription(), actualDataPojo.getDescription());
        assertEquals(expectedData.getStatus(), actualDataPojo.getStatus());

        //5.Validation(ObjectMapper)
        Room actualDataObjectMapper = new ObjectMapper().readValue(response.asString(), Room.class);
        assertEquals(expectedData.getRoomNumber(), actualDataObjectMapper.getRoomNumber());
        assertEquals(expectedData.getRoomType(), actualDataObjectMapper.getRoomType());
        assertEquals(expectedData.getPrice(), actualDataObjectMapper.getPrice());
        assertEquals(expectedData.getDescription(), actualDataObjectMapper.getDescription());
        assertEquals(expectedData.getStatus(), actualDataObjectMapper.getStatus());

        //6.Validation(Gson)
        Room actualDataGson = new Gson().fromJson(response.asString(), Room.class);
        assertEquals(expectedData.getRoomNumber(), actualDataGson.getRoomNumber());
        assertEquals(expectedData.getRoomType(), actualDataGson.getRoomType());
        assertEquals(expectedData.getPrice(), actualDataGson.getPrice());
        assertEquals(expectedData.getDescription(), actualDataGson.getDescription());
        assertEquals(expectedData.getStatus(), actualDataGson.getStatus());

    }

}
