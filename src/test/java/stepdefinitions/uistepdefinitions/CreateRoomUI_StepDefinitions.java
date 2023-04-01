package stepdefinitions.uistepdefinitions;

import base_urls.MedunnaBaseUrl;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.MedunnaHomePage;
import pages.MedunnaRoomPage;
import pages.MedunnaSignInPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateRoomUI_StepDefinitions extends MedunnaBaseUrl {

    MedunnaHomePage medunnaHomePage;
    MedunnaSignInPage medunnaSignInPage;
    MedunnaRoomPage medunnaRoomPage;
    public static int number;
    JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();


    @Given("{string} sayfasina gidilir")
    public void sayfasina_gidilir(String string) {
        Driver.getDriver().get(ConfigReader.getProperty("medunnaUrl"));
    }

    @When("admin olarak giris yapilir")
    public void admin_olarak_giris_yapilir() {
        medunnaHomePage = new MedunnaHomePage();
        medunnaSignInPage = new MedunnaSignInPage();

        medunnaHomePage.loginLinkButton.click();
        medunnaHomePage.signInButton.click();
        medunnaSignInPage.usernameBox.sendKeys(ConfigReader.getProperty("medunnaUsername"));
        medunnaSignInPage.passwordBox.sendKeys(ConfigReader.getProperty("medunnaPassword"));
        medunnaSignInPage.signInButton.click();

    }

    @When("itemsTitles menudeki room sayfasina gidilir")
    public void items_titles_menudeki_room_sayfasina_gidilir() {
        medunnaHomePage = new MedunnaHomePage();
        medunnaHomePage.itemsTitlesButton.click();
        medunnaHomePage.roomButton.click();
    }

    @When("create an new Room tiklanir")
    public void create_an_new_room_tiklanir() {
        medunnaRoomPage = new MedunnaRoomPage();
        medunnaRoomPage.newRoomButton.click();

    }

    @When("gecerli degerler girilerek room olusturulur")
    public void gecerli_degerler_girilerek_room_olusturulur() throws InterruptedException {

        medunnaRoomPage = new MedunnaRoomPage();

        number = Faker.instance().number().numberBetween(1000, 9999);
        System.out.println("UI'de olusturulan oda numarası = " + number);
        medunnaRoomPage.roomNumberBox.sendKeys("" + number);
        Select select = new Select(medunnaRoomPage.drapdownRoomTypeButton);
        select.selectByVisibleText("TWIN");
        medunnaRoomPage.statusRadioButton.click();
        medunnaRoomPage.priceBox.sendKeys(ConfigReader.getProperty("medunnaPrice"));
        medunnaRoomPage.descriptionBox.sendKeys(ConfigReader.getProperty("medunnaDescription"));
        js.executeScript("arguments[0].click();", medunnaRoomPage.saveButton);

    }

    public static void roomAssertion(List<WebElement> element, int number) {

        List<WebElement> list = element;
        for (WebElement w : list) {
            if (w.getText().contains("" + number)) {
                assertTrue(w.getText().contains("" + number));
                break;
//            }else {
//
//            }
            }
        }
    }

    @Then("room olusturuldugu dogrulanir")
    public void room_olusturuldugu_dogrulanir() {
        medunnaRoomPage = new MedunnaRoomPage();

        js.executeScript("arguments[0].click();", medunnaRoomPage.createdDateButton);
        roomAssertion(medunnaRoomPage.roomNumberList, number);

    }

    @And("sayfa kapatilir")
    public void sayfaKapatilir() {
        Driver.closeDriver();
    }

    static int id;

//    public static void idBulmaca(List<WebElement> element, int number) {
//
//        List<WebElement> list = element;
//
//
//        for (WebElement w : list) {
//            if (w.getText().contains("" + number)) {
//                String[] string = w.getText().split(" ", 0);
//                id = Integer.valueOf(string.toString());
//                System.out.println("id = " + id);
//                break;
//            }
//        }
//    }

//    @And("api ile room olusturuldugu dogrulanir")
//    public void apiIleRoomOlusturulduguDogrulanir() {
//
//        spec.pathParams("first", "api", "second", "rooms").queryParam("roomNumber", number);
//        Response response = given(spec).get("/{first}/{second}");
//        response.prettyPrint();
//        assertEquals(200, response.statusCode());
//        idBulmaca(medunnaRoomPage.idSatiri,number);

        //"findAll{it.roomNumber}.roomNumber"
        //JsonPath jsonPath = response.jsonPath();
        // System.out.println("list = " + list);
        //  System.out.println("list.size() = " + list.size());
        //assertTrue(list.contains(number));
        // 1. validation
//        response.then().
//                body("roomNumber", equalTo(number)).
//                body("roomType", equalTo("SUITE")).
//                body("status", equalTo(true)).
//                body("price", equalTo(ConfigReader.getProperty("medunnaPrice")));

    }

