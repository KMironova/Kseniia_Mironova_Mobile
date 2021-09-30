package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebPageObject extends PageObject {

    @FindBy(xpath = "//input[@name='q']")
    WebElement searchField;

    @FindBy(xpath = "//div[@id='rso']/div")
    WebElement results;

    public WebPageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }
}
