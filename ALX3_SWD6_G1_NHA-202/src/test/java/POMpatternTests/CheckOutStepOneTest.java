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
import org.testng.asserts.SoftAssert;

public class CheckOutStepOneTest {
    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions options = new ChromeOptions();
    CheckOutStepOne copy;
    SoftAssert softAssert=new SoftAssert();


    @BeforeMethod
    public void setup(){
        //WebDriverManager.chromedriver().setup(); //Suggested edit

        options.addArguments("--start-maximized --guest");
        driver = new ChromeDriver(options);
        copy= new CheckOutStepOne(driver);
//        driver.get("https://www.saucedemo.com/"); //Suggested edit
//        copy.CartNavigation("standard_user", "secret_sauce"); //Suggested edit
        copy.navigate(); //Suggested edit
    }

    @Test(dataProvider="CheckOutStepOneMissingField" ,dataProviderClass = TestData.class)
    public void fillOneMissingField(String firstName,String lastName,String postalCode, String expectedErrorMessage){
        copy.fillFirstName(firstName).fillLastName(lastName).fillZipCode(postalCode).clickContinue();
        String actualError= copy.missingDataAlertGetText();
        Assert.assertEquals(actualError,expectedErrorMessage);
    }
    @Test(dataProvider ="numbersAndSpecialCharacter", dataProviderClass = TestData.class)
    public void UsingSpecialCharacterInFirstAndLastNameAndPostalCode(String firstName,String lastName,String postalCode){
        copy.fillFirstName(firstName).fillLastName(lastName).fillZipCode(postalCode).clickContinue();
        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html","All fields accept special character");
        softAssert.assertEquals(copy.isAlertPresent(),true,"  Alert not Found");
        softAssert.assertAll();

    }
    @Test
    public void validLogInCredentialsInCheckOutStepOneTC5() {
        String pageTitle =copy.fillFirstName("Abdelrahman").fillLastName("Shalaby").fillZipCode("03").clickContinue().checkOutGetUrl();
        Assert.assertEquals(pageTitle,"https://www.saucedemo.com/checkout-step-two.html");
    }
    @AfterMethod
    public void tearDown(){driver.quit();}
}
