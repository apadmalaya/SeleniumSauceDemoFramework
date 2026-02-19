package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    private static WebDriver driver;

    //Get driver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            //set up chormeDriver automatically
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    //quit driver instances
    public static void quitDriver()
    {
        if(driver !=null)
        {
            driver.quit();
            driver = null;
        }
    }
}
