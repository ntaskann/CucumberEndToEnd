package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class MedunnaBaseUrl {

    public static RequestSpecification spec;

    public static void medunnaSetUp(){

        spec = new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                addHeader("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXRjaF95dXp1YyIsImF1dGgiOiJST0xFX0FETUlOIiwiZXhwIjoxNjgwMTIxNjU2fQ.Aaie5hSvgn_ALZktQJ25rx_EaY147lYxfUF3cok8Zs3QECuVY8VK8SQVPwQKDymFmRrea_0ojCKXc9s7Ab7_yg").
                setBaseUri("https://medunna.com").build();

    }
}
