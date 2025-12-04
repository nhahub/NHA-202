package POMpatternTests;

import POMpatternPages.LoginPage;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest {

    private WebDriver driver;
    LoginPage copy = new LoginPage(driver);

    //Login with Acceptance username and password

    @Test(dataProvider = "loginValidData",dataProviderClass = TestData.class)
    public void loginUsingValidCredentials(String userName, String password){
        copy.setLogin(userName,password).validation("https://www.saucedemo.com/inventory.html");
    }

    //invalid name and valid password
    @Test(dataProvider = "loginInvalidData",dataProviderClass = TestData.class)
    public void loginUsingInvalidCredentials(String userName, String password){
        copy.setLogin(userName, password).validation("https://www.saucedemo.com/");
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized --guest");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}

