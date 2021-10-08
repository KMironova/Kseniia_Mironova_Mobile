package scenarios;

import dataproviders.DataProviderForMobileTests;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageObjects.WebPageObject;
import setup.BaseTest;
import steps.AssertionSteps;
import java.util.List;

public class webMobileTests extends BaseTest {

    @Test(groups = {"web"}, description = "Open Google search and type 'EPAM'",
          dataProviderClass = DataProviderForMobileTests.class,
          dataProvider = "data for web mobile test")
    public void simpleWebTest(String url, String searchText) {
        getDriver().get(url);
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        WebPageObject webPageObject = new WebPageObject(getDriver());
        webPageObject.getSearchField().sendKeys(searchText + "\n");
        List<WebElement> result = webPageObject.getResults();

        AssertionSteps.verifyThatResultEqualsSearch(result, searchText);
    }
}
