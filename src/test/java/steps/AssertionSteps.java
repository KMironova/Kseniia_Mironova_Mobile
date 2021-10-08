package steps;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AssertionSteps {

    public static void verifyThatResultEqualsSearch(List<WebElement> result, String searchText) {
        for (WebElement searchElement : result) {
            if(searchElement.getText().contains(searchText)) {
                Assert.assertTrue(searchElement.getText().contains(searchText));
                return;
            }
        }
        Assert.fail("No equals result");
    }

    public static void verifyThatCurrentPageIsCorrect(String currentPageName, String expectedPageName) {
        Assert.assertEquals(currentPageName, expectedPageName);
    }
}