package setup;

import io.appium.java_client.AppiumDriver;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;
import utils.ConfProperties;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class BaseTest implements IDriver {
    private static AppiumDriver appiumDriver; // singleton
    IPageObject po;

    @Override
    public AppiumDriver getDriver() { return appiumDriver; }

    public IPageObject getPo() {
        return po;
    }

    @Parameters({"platformName","appType","deviceName","udid","browserName","app","appPackage","appActivity","bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setUp(String platformName,
                      String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        System.out.println("Before: app type - "+appType);
        setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    @SneakyThrows
    private void setAppiumDriver(String platformName, String deviceName, String udid, String browserName, String app,
                                 String appPackage, String appActivity, String bundleId){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);

        if(app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck","true");

        // Capabilities for test of Android native app on EPAM Mobile Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        // Capabilities for test of iOS native app on EPAM Mobile Cloud
        capabilities.setCapability("bundleId",bundleId);
        if(platformName.equals("iOS")) capabilities.setCapability("automationName","XCUITest");

        try {
            String key = URLEncoder.encode(ConfProperties.getProperty("API_KEY"), StandardCharsets.UTF_8.name());
            appiumDriver = new AppiumDriver(
                    new URL(format("https://%s:%s@%s/wd/hub", ConfProperties.getProperty("PROJECT_NAME"),
                                                         key, ConfProperties.getProperty("APPIUM_HUB"))), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        po = new PageObject(appType, appiumDriver);
    }
}
