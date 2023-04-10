package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class GridStepDefinitions {

    WebDriver driver;

    @Given("user is on the homepage {string} by chrome")
    public void user_is_on_the_homepage_by_chrome(String url) throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://192.168.43.144:4444"), new ChromeOptions());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Then("verify title is {string}")
    public void verify_title_is(String title) {
        assertEquals(title, driver.getTitle());
    }

    @Then("close the remote driver")
    public void close_the_remote_driver() {
        driver.close();
    }

    @Given("user is on the homepage {string} by edge")
    public void userIsOnTheHomepageByEdge(String url) throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://192.168.43.144:4444"), new EdgeOptions());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Given("user is on the homepage {string} by firefox")
    public void userIsOnTheHomepageByFirefox(String url) throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://192.168.43.144:4444"), new FirefoxOptions());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);

    }

}
