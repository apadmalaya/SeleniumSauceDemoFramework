package tests;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import jdk.jfr.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.ByteArrayInputStream;

@Listeners({AllureTestNg.class})
public class LoginTest extends BaseTest {

    @Test(description = "Valid login with standard user")
    @Description("Login with valid credentials and verify the login is successful")
    public void validLoginTest()
    {
        Allure.step("Opening SauceDemo website");
        LoginPage loginPage= new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Allure.addAttachment("Login Screenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));

        //verify successful login by clicking url
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),"Login Failed");
    }

    @Test(description = "Invalid login with standard user")
    @Description(("Login with invalid credentials and verify error message"))
    public void invalidLoginTest()
    {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.login("standard_user", "scret_sauce");
        Allure.addAttachment("Login Screenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));

        //verify successful login by clicking url
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"),"Error message not displayed");

    }
}
