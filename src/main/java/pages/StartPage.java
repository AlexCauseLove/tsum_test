package pages;

import io.appium.java_client.pagefactory.WithTimeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class StartPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@href='/login/']")
    @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
    private WebElement personalLoginInfoLink;

    @FindBy(xpath = "//img[@class='header__logo-icon']")
    @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
    private WebElement logoIcon;

    @FindBy(xpath = "//a[@href='/personal/orders/']")
    @WithTimeout(time = 10, unit = TimeUnit.SECONDS)
    private WebElement personalOrdersInfoLink;

    public StartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isStartPageWithoutAuthorizedLoaded() {
        final WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/login/']")));
        return logoIcon.isDisplayed() && personalLoginInfoLink.isDisplayed();
    }

    public boolean isStartPageWithAuthorizedLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/personal/orders/']")));
        return logoIcon.isDisplayed() && personalOrdersInfoLink.isDisplayed();
    }

    public WebElement getPersonalLoginInfoLink() {
        return personalLoginInfoLink;
    }

    public WebElement getPersonalOrdersInfoLink() {
        return personalOrdersInfoLink;
    }
}
