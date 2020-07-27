package steps;

import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import pages.StartPage;

import java.util.concurrent.TimeUnit;

import static constants.UrlConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StartPageSteps {

    private WebDriver driver;
    private StartPage startPage;

    public StartPageSteps(WebDriver driver) {
        this.driver = driver;
        startPage = new StartPage(driver);
    }

    @Step(value = "open start page")
    public void openStartPage() {
        driver.get(START_PAGE_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Step(value = "check start page without authorization is displayed")
    public void checkStartPageWithoutAuthorizationIsLoaded() {
        assertThat("Error: Start page without authorization is not displayed!",
                startPage.isStartPageWithoutAuthorizedLoaded());
    }

    @Step(value = "check that authorized user is logged in with mail {0}")
    public void checkUserIsLoggedIn(final String mail) {
        checkStartPageWithAuthorizationIsLoaded();
        assertThat("Error: Start page with authorization is not displayed!",
                startPage.getPersonalOrdersInfoLink().getText(),
                equalTo(mail));
    }

    @Step(value = "check start page with authorization is displayed")
    public void checkStartPageWithAuthorizationIsLoaded() {
        assertThat("Error: Start", startPage.isStartPageWithAuthorizedLoaded());
    }

    @Step(value = "open login page by personal area link")
    public void goToLoginPage() {
        startPage.getPersonalLoginInfoLink().click();
    }

    @Step(value = "open start page and go to auth page")
    public void openAuthPage() {
        openStartPage();
        checkStartPageWithoutAuthorizationIsLoaded();
        goToLoginPage();
    }
}
