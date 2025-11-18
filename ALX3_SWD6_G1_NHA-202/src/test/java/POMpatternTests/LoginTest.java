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


    @Test
    public void LoginTC1(){
         new LoginPage(driver).
        setLogin("standard_user", "secret_sauce").
      validation("https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void LoginTC2(){
        new LoginPage(driver).
                setLogin("locked_out_user", "secret_sauce").
                validation("https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void LoginTc3(){
        new LoginPage(driver).
                setLogin("problem_user", "secret_sauce").
                validation("https://www.saucedemo.com/inventory.html");
    }



@Test
public void LoginTc4(){
    new LoginPage(driver).
            setLogin("performance_glitch_user", "secret_sauce").
            validation("https://www.saucedemo.com/inventory.html");
}



@Test
public void LoginTc5(){
    new LoginPage(driver).
            setLogin("error_user", "secret_sauce").
            validation("https://www.saucedemo.com/inventory.html");
}






@Test

public void LoginTc6(){
    new LoginPage(driver).
            setLogin("visual_user", "secret_sauce").
            validation("https://www.saucedemo.com/inventory.html");
}










    @BeforeMethod
    public void setUp(){
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--start-maximized --guest");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver=new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");

    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}

