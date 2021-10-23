package scenarios;

import dataproviders.DataProviderForMobileTests;
import org.testng.annotations.Test;
import pageObjects.NativePageObject;
import setup.BaseTest;
import steps.AssertionSteps;

public class nativeMobileTests extends BaseTest {

    @Test(groups = "native", description = "Register new account and then sign in",
          dataProviderClass = DataProviderForMobileTests.class,
          dataProvider = "data for native mobile test")
    public void registerAndSignInTest(String email, String password, String username) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        NativePageObject nativePageObject = new NativePageObject(getDriver());

        nativePageObject.getRegisterBtn().click();
        nativePageObject.getEmailField().sendKeys(email);
        nativePageObject.getPasswordRegField().sendKeys(password);
        nativePageObject.getUsernameField().sendKeys(username);
        nativePageObject.getPasswordConfirmRegField().sendKeys(password);
        nativePageObject.getRegisterNewAccountButton().click();
        nativePageObject.getLoginEmailField().sendKeys(email);
        nativePageObject.getLoginPasswordField().sendKeys(password);
        nativePageObject.getSignInBtn().click();

        AssertionSteps.verifyThatCurrentPageIsCorrect(nativePageObject.getBudgetActivity());
    }
}
