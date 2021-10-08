package steps;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AssertionSteps {

    public static void verifyThatResultEqualsSearch(List<WebElement> result, String searchText) {
        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).getText().equals(searchText)) {
                Assert.assertTrue(result.contains(searchText));
                return;
            }
        }
        Assert.fail("No equals result");
    }

    public static void verifyThatCurrentPageIsCorrect(String currentPageName, String expectedPageName) {
        Assert.assertEquals(currentPageName, expectedPageName);
    }
}
