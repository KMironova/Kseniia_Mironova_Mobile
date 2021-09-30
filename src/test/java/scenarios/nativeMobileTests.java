package scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;
import setup.BaseTest;
import utils.ConfProperties;

public class nativeMobileTests extends BaseTest {

    @Test(groups = "native", description = "Register new account and then sign in")
    public void registerAndSignInTest() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        getPo().getWebElement("registerBtn").click();
        getPo().getWebElement("emailField")
                .sendKeys(ConfProperties.getProperty("email"));
        getPo().getWebElement("passwordRegField")
                .sendKeys(ConfProperties.getProperty("password"));
        getPo().getWebElement("usernameField")
                .sendKeys(ConfProperties.getProperty("username"));
        getPo().getWebElement("passwordConfirmRegField")
                .sendKeys(ConfProperties.getProperty("password"));
        getPo().getWebElement("registerNewAccountButton").click();
        getPo().getWebElement("loginEmailField")
                .sendKeys(ConfProperties.getProperty("email"));
        getPo().getWebElement("loginPasswordField")
                .sendKeys(ConfProperties.getProperty("password"));
        getPo().getWebElement("signInBtn").click();

        Assert.assertEquals(getPo().getWebElement("budgetActivity").getText(),"BudgetActivity");
    }
}
