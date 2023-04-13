package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RemoteDriver {

    private RemoteDriver() {

    }

    private static WebDriver driver;

    public static WebDriver getDriver(String hub, String browser) {
        if (driver == null) {
            switch (browser) {
                case "chrome":
                    try {
                        driver = new RemoteWebDriver(new URL(hub), new ChromeOptions());
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "edge":
                    try {
                        driver = new RemoteWebDriver(new URL(hub), new EdgeOptions());
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "firefox":
                    try {
                        driver = new RemoteWebDriver(new URL(hub), new FirefoxOptions());
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        return driver;


    }


    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

}
