package steps;

import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import pages.AuthPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthPageSteps {

    private WebDriver driver;
    private AuthPage authPage;

    public AuthPageSteps(WebDriver driver) {
        this.driver = driver;
        authPage = new AuthPage(driver);
    }

    @Step(value = "check that auth page is displayed")
    public void checkPageIsDisplayed() {
        assertThat("Error: Start page is not loaded!", authPage.isPageLoaded());
    }

    @Step(value = "check that authorization form is activate")
    public void checkAuthFormIsActive() {
        final String registrationAttr = authPage.getAuthorizationFieldSwitcher().getAttribute("class");
        assertThat("Error: Authorization form not activate",
                registrationAttr, containsString("active"));
    }

    @Step(value = "enter characters {0} into e-mail field")
    public void enterCharactersIntoEmailField(String s) {
        authPage.getEmailTextField().sendKeys(s);
    }

    @Step(value = "enter characters {0} into password field")
    public void enterCharactersIntoPasswordField(String s) {
        authPage.getPasswordTextField().sendKeys(s);
    }

    @Step(value = "press submit button")
    public void pressSubmitButton() {
        authPage.getEnterButton().click();
    }

    @Step(value = "enter mail {0} and password {1} int field and press submit")
    public void enterMailAndPasswordAndPressSubmit(final String mail, final String password) {
        enterCharactersIntoEmailField(mail);
        enterCharactersIntoPasswordField(password);
        pressSubmitButton();
    }

    @Step(value = "activate registration control button")
    public void activateRegistrationControlButton() {
        authPage.getRegistrationFieldSwitcher().click();
    }

    @Step(value = "check that form switch to registration")
    public void checkRegistrationFormIsActive() {
        final String registrationAttr = authPage.getRegistrationFieldSwitcher().getAttribute("class");
        assertThat("Error: Registration form not activated by control button",
                registrationAttr, containsString("active"));
        final String authorizationAttr = authPage.getAuthorizationFieldSwitcher().getAttribute("class");
        assertThat("Error: Authorization form is activate",
                authorizationAttr, not(containsString("active")));
    }

    @Step(value = "check notice error message {0} is displayed")
    public void checkNoticeErrorWithMessageIsDisplayed(final String errorMessage) {
        assertThat("Error: Error notice is not displayed",
                authPage.isErrorNoticeDisplayed());
        assertThat("Error: Error message form web form and expected are different!",
                authPage.getErrorNotice().getText(), equalTo(errorMessage));
    }
}
