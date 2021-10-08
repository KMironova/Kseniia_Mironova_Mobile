package dataproviders;

import org.testng.annotations.DataProvider;
import utils.ConfProperties;

public class DataProviderForMobileTests {

    @DataProvider(name = "data for native mobile test")
    public static Object [][] dataForNativeMobileTest() {
        return new Object[][] {
                {ConfProperties.getProperty("email"),
                 ConfProperties.getProperty("password"),
                 ConfProperties.getProperty("username")}
        };
    }

    @DataProvider(name = "data for web mobile test")
    public static Object [][] dataForWebMobileTest() {
        return new Object[][] {
                {ConfProperties.getProperty("url"), ConfProperties.getProperty("searchText")}
        };
    }
}
