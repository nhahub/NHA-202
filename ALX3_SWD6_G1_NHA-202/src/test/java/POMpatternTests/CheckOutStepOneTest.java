package POMpatternTests;

import POMpatternPages.CheckOutStepOne;
import POMpatternPages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckOutStepOneTest {
    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions options = new ChromeOptions();
    CheckOutStepOne copy;


    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        options.addArguments("--start-maximized --disable-notifications");
        driver = new ChromeDriver(options);
        copy= new CheckOutStepOne(driver);
        driver.get("https://www.saucedemo.com/");
        copy.CartNavigation("standard_user", "secret_sauce");
    }









    @Test
    public void emptyFirstNameInCheckOutStepOneTC1(){
        copy.fillFirstName("");
        copy.fillLastName("Shalaby");
        copy.fillZipCode("03");
        copy.clickContinue();
        Assert.assertEquals(copy.missingDataAlertGetText(),"Error: First Name is required");
    }

    @Test
    public void emptyLastNameInCheckOutStepOneTC2() {
        copy.fillFirstName("Abdelrahman");
        copy.fillLastName("");
        copy.fillZipCode("03");
        copy.clickContinue();
        Assert.assertEquals(copy.missingDataAlertGetText(), "Error: Last Name is required");
    }
    @Test
    public void emptyPostalCodeInCheckOutStepOneTC3() {
        copy.fillFirstName("Abdelrahman");
        copy.fillLastName("Shalaby");
        copy.fillZipCode("");
        copy.clickContinue();
        Assert.assertEquals(copy.missingDataAlertGetText(), "Error: Error: Postal Code is required");
    }
    @Test
    public void specialCharacterInFistNameCheckOutStepOneTC4(){
        copy.fillFirstName("#@#$");
        copy.fillLastName("Shalaby");
        copy.fillZipCode("03");
        copy.clickContinue();
        Assert.assertEquals(copy.missingDataAlertGetText(),"Error: First Name is only alphabet");
    }
    @Test
    public void validLogInCredentialsInCheckOutStepOneTC5() {
        String pageTitle =copy.fillFirstName("Abdelrahman").fillLastName("Shalaby").fillZipCode("03").clickContinue().checkOutGetUrl();
        Assert.assertEquals(pageTitle,"https://www.saucedemo.com/checkout-step-two.html");
    }
    @AfterMethod
    public void tearDown(){driver.quit();}
}
