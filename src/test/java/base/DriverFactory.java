package base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {
    //private static WebDriver driver;
    // ThreadLocal for thread-safe driver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //Get driver instance
    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            if(browser.equalsIgnoreCase("chrome")) {
                //set up chormeDriver automatically
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
            }
            else if(browser.equalsIgnoreCase("edge"))
            {
                WebDriverManager.edgedriver().clearDriverCache().setup();
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--remote-allow-origins=*");
               // driver = new EdgeDriver(options);
                driver.set(new EdgeDriver());
            }
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    //quit driver instances
    public static void quitDriver()
    {
        if(driver.get() !=null)
        {
            driver.get().quit();
            driver.remove();
        }
    }
}
