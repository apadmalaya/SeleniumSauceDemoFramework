package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    //Constructor
    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
     }

    //WebElements using @FindBy
    @FindBy(id="user-name")
    private WebElement usernameField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css ="h3[data-test='error']")
    private WebElement errorMessage;

    //Steps for Allure
    @Step("Enter username: {0}")
    public void enterUserName(String username)
    {
        usernameField.sendKeys(username);
    }

    @Step("Enter password: {0}")
    public void enterPassword(String password)
    {
        passwordField.sendKeys(password);
    }
    @Step("Click login button")
    public void clickLogin()
    {
        loginButton.click();
    }
    @Step("Get login error message")
    public String getErrorMessage()
    {
        return  errorMessage.getText();
    }
    // Full login
    @Step("Login with username :{0} and password {1}")
    public void login(String username, String password)
    {
        enterUserName(username);
        enterPassword(password);
        clickLogin();
    }
}
