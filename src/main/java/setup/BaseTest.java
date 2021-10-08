package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

public class BaseTest implements IDriver {
    private static final String API_KEY = "7AQUpo8nHyYjH/SdikEaJvu5Wv5ny0q7KR5Ed6H2f3u6a6a5hI2fHZwOngJ2i1sPnqBz+LmH10A9HMCwYJFFOVe7H+olXi3T2KWEUIml2FXy/6Ue9Dip3hfPCYinCPe2ajZhtDYuanGNRwnEkQYDHcoExMMPtAHFrVTHvcP5lzvX6ayHg5LPSjJ6RuFPZm2G6RmU7dsFZY6eu3H84TlT/pit8w10qixcZAhOr40TJ0CDjtC0S0AGij1C46M+YBnsDoOjZpJi5LUbb6CqGYLE0zDiDRpKLDQTwrgHwd6iNTppI4zIs+vQV1BRFXSW2H1dEeq4vSbgXxB7K2d6HbNgRIGCAAfquW+G4JJO2U56f5u0zyMqzLhUPvNMMApBzvhKW8N85Cjy/6l3+mELBddlcTEtpKkd+WnhXMrjZuwy5mOPS6XpRSx7hVo5/K7OPE/hHwbajjqTUXpHP5+YcwKMEZNQwMFU5CajQTQCMNevpAJdtI5AY1/LFMZeHUcvDGfwsF6RTmCUnrz7CkaQlmP+Fb/3cctFvMzq0wQ9GeRE06EVlHSrbWRRNYePRi6zgWhlVfDIAtlyIIcDJ5sr4iAOftNmT+OQXvpF55PB6m+u7k3684JFdZ9799L4qCc28IFGq9yx3BhC6U1WTRt22/a8HiJWON4JZwaQZ0bJs7iQbAgSkJ8nCLQO1TXIxHc5xd/2LHfSRKOTLqmuy8REzPecIAc6zS28IeECIZvraDCdcIig9sMiuez6jElgo+Eyx7arsg/JwtDQYjOuDeG8eROfRs+hpZWoyml3teWyKqFXillwQx6/nPgRgkgAuRfip5H7pZe3P3HmY49iXUxuySaFEI1pueFTpGihubkrii6V1/xkZvVkS8Y+MDz1JWQ+6Y0s/4aHHjZpSZuD30QDmVFToAo";
    private static final String PROJECT_NAME = "EPM-TSTF";
    private static final String APPIUM_HUB = "mobilecloud.epam.com";

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
    public void tearDown() throws Exception {
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
            String key = URLEncoder.encode(API_KEY, StandardCharsets.UTF_8.name());
            appiumDriver = new AppiumDriver(
                    new URL(format("https://%s:%s@%s/wd/hub", PROJECT_NAME, key, APPIUM_HUB)), capabilities);
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
