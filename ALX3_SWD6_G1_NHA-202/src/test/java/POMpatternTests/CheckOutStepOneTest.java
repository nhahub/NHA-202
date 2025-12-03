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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckOutStepOneTest {
    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions options = new ChromeOptions();
    CheckOutStepOne copy;


    @BeforeMethod
    public void setup(){
        //WebDriverManager.chromedriver().setup(); //Suggested edit
        //options.addArguments("--start-maximized --disable-notifications"); //Suggested edit
        options.addArguments("--start-maximized --guest");
        driver = new ChromeDriver(options);
        copy= new CheckOutStepOne(driver);
//        driver.get("https://www.saucedemo.com/"); //Suggested edit
//        copy.CartNavigation("standard_user", "secret_sauce"); //Suggested edit
        copy.navigate(); //Suggested edit
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
        Assert.assertEquals(copy.missingDataAlertGetText(), "Error: Postal Code is required"); //Suggested edit
        //Assert.assertEquals(copy.missingDataAlertGetText(), "Error: Error: Postal Code is required");
    }

    @Test(dataProvider="CheckOutStepOneMissingField" ,dataProviderClass = TestData.class)
    public void fillOneMissingField(String firstName,String lastName,String postalCode, String expectedErrorMessage){
        copy.fillFirstName(firstName).fillLastName(lastName).fillZipCode(postalCode).clickContinue();
        String actualError= copy.missingDataAlertGetText();
        Assert.assertEquals(actualError,expectedErrorMessage);
    }
    @Test
    public void specialCharacterInFistNameCheckOutStepOneTC4(){
        copy.fillFirstName("#@#$");
        copy.fillLastName("Shalaby");
        copy.fillZipCode("03");
        copy.clickContinue();
        //Assert.assertEquals(copy.missingDataAlertGetText(),"Error: First Name is only alphabet");
    }
    @Test
    public void validLogInCredentialsInCheckOutStepOneTC5() {
        String pageTitle =copy.fillFirstName("Abdelrahman").fillLastName("Shalaby").fillZipCode("03").clickContinue().checkOutGetUrl();
        Assert.assertEquals(pageTitle,"https://www.saucedemo.com/checkout-step-two.html");
    }
    @AfterMethod
    public void tearDown(){driver.quit();}
}
