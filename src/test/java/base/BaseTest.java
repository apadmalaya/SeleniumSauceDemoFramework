package base;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    private long startTime;
    @BeforeMethod
    @Parameters({"browser","headless"})
    public void setup(String browser, String headlessParam) {
        //initialize webdriver
        boolean headless = Boolean.parseBoolean(headlessParam);
        startTime = System.currentTimeMillis();
        driver = DriverFactory.getDriver(browser,headless);
       driver.get("https://www.saucedemo.com/");
    }
    @AfterMethod
    public void tearDown()
    {
        long endtime = System.currentTimeMillis();
        long duration  = endtime -startTime;
//        System.out.println("Test executed in: " + duration/1000.0 + " seconds");
        Allure.step("Test executed in: \" + duration/1000.0 + \" seconds");
        DriverFactory.quitDriver();
    }
}
