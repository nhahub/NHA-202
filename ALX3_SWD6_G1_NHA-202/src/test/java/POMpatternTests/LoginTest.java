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
    @Test
    public void StandardUserLogin() {
        new LoginPage(driver).
                setLogin("standard_user", "secret_sauce").
                validation("https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void lockedUserLogin() {
        new LoginPage(driver).
                setLogin("locked_out_user", "secret_sauce").
                validation("https://www.saucedemo.com/");
    }

    @Test
    public void problemUserLogin() {
        new LoginPage(driver).
                setLogin("problem_user", "secret_sauce").
                validation("https://www.saucedemo.com/inventory.html");

    }


    @Test
    public void PerformanceLogin() {
        new LoginPage(driver).
                setLogin("performance_glitch_user", "secret_sauce").
                validation("https://www.saucedemo.com/inventory.html");
    }


   @Test
    public void errorUserLogin() {
        new LoginPage(driver).
                setLogin("error_user", "secret_sauce").
                validation("https://www.saucedemo.com/inventory.html");
    }


    @Test

    public void visualUserLogin() {
        new LoginPage(driver).
                setLogin("visual_user", "secret_sauce").
                validation("https://www.saucedemo.com/inventory.html");
    }
    @Test(dataProvider = "loginValidData",dataProviderClass = TestData.class)
    public void loginUsingValidCredentials(String userName, String password){
        copy.setLogin(userName,password).validation("https://www.saucedemo.com/inventory.html");
    }

        //invalid name and valid password
    @Test
    public void InvalidName() {
        new LoginPage(driver).
                setLogin("swag", "secret_sauce").
                validation("https://www.saucedemo.com/");
    }

    @Test
//valid name and invalid password(case sensitivity using one capital letter)
    public void InvalidPassword() {
        new LoginPage(driver).
                setLogin("standard_user", "Secret_sauce").
                validation("https://www.saucedemo.com/");
    }
@Test

public void emptyName() {
    new LoginPage(driver).
            setLogin("", "Secret_sauce").
            validation("https://www.saucedemo.com/");

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

