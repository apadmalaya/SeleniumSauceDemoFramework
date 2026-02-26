package base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {
    //private static WebDriver driver;
    // ThreadLocal for thread-safe driver
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //Get driver instance
    public static WebDriver getDriver(String browser, boolean headless) {
        if (driver.get() == null) {
            if(browser.equalsIgnoreCase("chrome")) {
                //set up chormeDriver automatically
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                if (headless) options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }
            else if(browser.equalsIgnoreCase("edge"))
            {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--remote-allow-origins=*");
                if (headless) options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
               // driver = new EdgeDriver(options);
                driver.set(new EdgeDriver(options));
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
