package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import setup.BaseTest;
import utils.ConfProperties;

public class webMobileTests extends BaseTest {

    @Test(groups = "web", description = "Open Google search and type 'EPAM'")
    public void simpleWebTest() throws Exception {
        getDriver().get("https://www.google.com");
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
        setPageObject("web", getDriver());
        getPo().getWebElement("searchField")
               .sendKeys(ConfProperties.getProperty("searchText") + "\n");
        String result = getPo().getWebElement("results").getText();

        Assert.assertTrue(result.contains(ConfProperties.getProperty("searchText")));
    }
}
