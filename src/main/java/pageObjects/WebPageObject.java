package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class WebPageObject extends PageObject {

    @FindBy(xpath = "//input[@name='q']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Пошук']")
    WebElement searchField;

    @FindBy(xpath = "//div[@id='rso']//div[contains(@class, 'g')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='epam - Пошук Google']")
    List<WebElement> results;

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }
}
