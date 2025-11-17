package POMpatternPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    // variables
    private final WebDriver driver;

    //locators
     private final By username=By.id("user-name");
     private final By password= By.id("password");
     private final By loginButton=By.xpath("//input[@type='submit']");


     // constructor
    public LoginPage(WebDriver driver){
        this.driver= driver;
    }


    // Actions
    public LoginPage setLogin(String userName, String pass){
        driver.findElement(username).sendKeys(userName);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginButton).click();
        return this;
    }

    // validation

    public ProductsPage validation(String expectedUrl){
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
       return null;

    }
}
