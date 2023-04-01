package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationMedunna {

    public static String generateToken() {

        String url = "https://medunna.com/api/authenticate";

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("username", "batch_yuzuc");
        bodyMap.put("password", "Batch.103");
        bodyMap.put("rememberMe", true);

        Response response = given().contentType(ContentType.JSON).body(bodyMap).post(url);

        return response.jsonPath().getString("id_token");

    }

}
