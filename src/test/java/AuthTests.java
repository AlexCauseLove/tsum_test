import core.WebDriverInstaller;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.AuthPageSteps;
import steps.StartPageSteps;

import static constants.NoticeConstants.WRONG_PASSWORD_OR_LOGIN;
import static constants.TsumCredentialsConstants.CUSTOMER_MAIL;
import static constants.TsumCredentialsConstants.CUSTOMER_PASS;
import static helpers.DataGenerator.generateRandomPasswordAsStr;

@RunWith(SerenityRunner.class)
public class AuthTests {

    private static WebDriver driver;

    @Steps
    private static StartPageSteps startPageSteps;
    @Steps
    private static AuthPageSteps authPageSteps;

    @BeforeClass
    public static void registeredWebDriver() {
        driver = WebDriverInstaller.installWebDriver();
        startPageSteps = new StartPageSteps(driver);
        authPageSteps = new AuthPageSteps(driver);
        driver.manage().window().maximize();
    }

    @Test
    public void authorizationInTsumByCreatedUser() {
        // when
        startPageSteps.openAuthPage();

        // then
        authPageSteps.checkPageIsDisplayed();
        authPageSteps.checkAuthFormIsActive();

        // when
        authPageSteps.enterMailAndPasswordAndPressSubmit(CUSTOMER_MAIL, CUSTOMER_PASS);

        // then
        startPageSteps.checkUserIsLoggedIn(CUSTOMER_MAIL);
    }

    @Test
    public void authorizationWithWrongPassword() {
        // when
        startPageSteps.openAuthPage();

        // then
        authPageSteps.checkPageIsDisplayed();
        authPageSteps.checkAuthFormIsActive();

        // when
        authPageSteps.enterMailAndPasswordAndPressSubmit(CUSTOMER_MAIL, generateRandomPasswordAsStr(10));

        // then
        authPageSteps.checkNoticeErrorWithMessageIsDisplayed(WRONG_PASSWORD_OR_LOGIN);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
