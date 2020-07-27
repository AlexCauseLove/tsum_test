package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public final class WebDriverInstaller {

    public static WebDriver installWebDriver() {

        final String driverName = System.getProperty("driver");
        if (Objects.nonNull(driverName) && driverName.equals(FIREFOX)) {
            System.setProperty("webdriver.firefox.driver", "src/main/resources");
            return new FirefoxDriver();
        }
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\alexa\\Projects\\tsum-test\\src\\main\\resources\\chromedriver.exe");
        return new ChromeDriver();
    }
}
