import core.WebDriverInstaller;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import steps.AuthPageSteps;
import steps.StartPageSteps;

import static constants.NoticeConstants.PASSWORD_LENGTH_ERROR;
import static helpers.DataGenerator.*;

@RunWith(SerenityRunner.class)
public class RegistrationTests {

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
    public void registrationNewUser() {
        // when
        startPageSteps.openAuthPage();

        // then
        authPageSteps.checkPageIsDisplayed();

        // when
        authPageSteps.activateRegistrationControlButton();

        // then
        authPageSteps.checkRegistrationFormIsActive();

        // when
        final String mail = generateRandomEmailAsStr();
        final String password = generateRandomPasswordAsStr(8);
        authPageSteps.enterMailAndPasswordAndPressSubmit(mail, password);

        // then
        startPageSteps.checkUserIsLoggedIn(mail);

        // add method to check log and pass into DB
    }

    @Test
    public void registrationNewUserWithInvalidPasswordLength() {
        // when
        startPageSteps.openAuthPage();

        // then
        authPageSteps.checkPageIsDisplayed();

        // when
        authPageSteps.activateRegistrationControlButton();

        // then
        authPageSteps.checkRegistrationFormIsActive();

        // when
        final String mail = generateRandomEmailAsStr();
        final String password = generateRandomPasswordAsStr(7);
        authPageSteps.enterMailAndPasswordAndPressSubmit(mail, password);

        // then
        authPageSteps.checkNoticeErrorWithMessageIsDisplayed(PASSWORD_LENGTH_ERROR);

        // add method to check log and pass not added into DB
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
