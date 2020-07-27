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

public class AuthPage {

    public WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[text()=' Авторизация ']")
    private WebElement authorizationFieldSwitcher;

    @FindBy(xpath = "//p[text()=' Регистрация ']")
    private WebElement registrationFieldSwitcher;

    @FindBy(xpath = "//div[text()=' Личный кабинет ']")
    private WebElement personalAreaBanner;

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement emailTextField;

    @FindBy(xpath = "//input[@placeholder='Пароль']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//button[@type='submit'][@theme='black']")
    private WebElement enterButton;

    @FindBy(xpath = "//div[@class='notice error']/span")
    private WebElement errorNotice;

    public WebElement getEnterButton() {
        return enterButton;
    }

    public boolean isPageLoaded() {
        return authorizationFieldSwitcher.isDisplayed()
                && registrationFieldSwitcher.isDisplayed()
                && personalAreaBanner.isDisplayed()
                && personalAreaBanner.getText().trim().equals("Личный кабинет");
    }

    public boolean isErrorNoticeDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='notice error']/span")));
        return errorNotice.isDisplayed();
    }

    public WebElement getRegistrationFieldSwitcher() {
        return registrationFieldSwitcher;
    }

    public WebElement getAuthorizationFieldSwitcher() {
        return authorizationFieldSwitcher;
    }

    public WebElement getEmailTextField() {
        return emailTextField;
    }

    public WebElement getErrorNotice() {
        return errorNotice;
    }

    public WebElement getPasswordTextField() {
        return passwordTextField;
    }
}
