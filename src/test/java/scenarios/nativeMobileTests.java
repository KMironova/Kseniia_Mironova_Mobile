package scenarios;

import dataproviders.DataProviderForMobileTests;
import org.testng.annotations.Test;
import setup.BaseTest;
import steps.AssertionSteps;

public class nativeMobileTests extends BaseTest {

    @Test(groups = {"native"}, description = "Register new account and then sign in",
          dataProviderClass = DataProviderForMobileTests.class,
          dataProvider = "data for native mobile test")
    public void registerAndSignInTest(String email, String password, String username) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        getPo().getWebElement("registerBtn").click();
        getPo().getWebElement("emailField").sendKeys(email);
        getPo().getWebElement("passwordRegField").sendKeys(password);
        getPo().getWebElement("usernameField").sendKeys(username);
        getPo().getWebElement("passwordConfirmRegField").sendKeys(password);
        getPo().getWebElement("registerNewAccountButton").click();
        getPo().getWebElement("loginEmailField").sendKeys(email);
        getPo().getWebElement("loginPasswordField").sendKeys(password);
        getPo().getWebElement("signInBtn").click();

        AssertionSteps.verifyThatCurrentPageIsCorrect(getPo().getWebElement("budgetActivity").getText(),
                "BudgetActivity");
    }
}
