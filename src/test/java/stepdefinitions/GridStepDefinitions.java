package stepdefinitions;

import io.cucumber.java.en.*;
import utilities.RemoteDriver;

import static org.junit.Assert.assertEquals;

public class GridStepDefinitions {

    //WebDriver driver;
     String hub = "http://192.168.1.28:4444";

    @Given("user is on the homepage {string} by {string}")
    public void user_is_on_the_homepage_by_chrome(String url, String browser) {
//        driver = new RemoteWebDriver(new URL("http://192.168.1.28:4444"), new ChromeOptions());
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.get(url);
        RemoteDriver.getDriver(hub, browser).get(url);
    }

    @Then("verify title is {string} by {string}")
    public void verify_title_is(String title, String browser) {
        assertEquals(title, RemoteDriver.getDriver(hub, browser).getTitle());
        System.out.println("Test basarili");
    }

    @Then("close the remote driver")
    public void close_the_remote_driver() {
        RemoteDriver.closeDriver();
    }



}
