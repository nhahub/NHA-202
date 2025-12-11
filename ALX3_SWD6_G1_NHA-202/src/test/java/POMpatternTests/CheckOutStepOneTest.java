package POMpatternTests;

import POMpatternPages.CartPage;
import POMpatternPages.CheckOutStepOne;
import POMpatternPages.LoginPage;
import POMpatternPages.ProductsPage;
import ParallelExecution.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
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

public class CheckOutStepOneTest extends BaseTest {
//    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckOutStepOne checkOutStepOne;
    SoftAssert softAssert=new SoftAssert();

    @Override
    protected void differentSetupMethod(){
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkOutStepOne = new CheckOutStepOne(driver);
        driver.get("https://www.saucedemo.com/");
        loginPage.setLogin("standard_user","secret_sauce");
        productsPage.addFirstProductToCart().addSecondProductToCart().goToCart();
        cartPage.clickCheckButton();
    }
    @Test
    public void validLogInCredentialsInCheckOutStepOneTC5() {
        String pageTitle = new CheckOutStepOne(driver).fillFirstName("Abdelrahman").fillLastName("Shalaby").fillZipCode("03").clickContinue().checkOutGetUrl();
        Assert.assertEquals(pageTitle,"https://www.saucedemo.com/checkout-step-two.html");
    }
    @Test(dataProvider="CheckOutStepOneMissingField" ,dataProviderClass = TestData.class)
    public void fillOneMissingField(String firstName,String lastName,String postalCode, String expectedErrorMessage){
        checkOutStepOne.fillFirstName(firstName).fillLastName(lastName).fillZipCode(postalCode).clickContinue();
        String actualError= checkOutStepOne.missingDataAlertGetText();
        Assert.assertEquals(actualError,expectedErrorMessage);
        System.out.println(" all is good ");
    }
    @Test(dataProvider ="numbersAndSpecialCharacter", dataProviderClass = TestData.class)
    public void UsingSpecialCharacterInFirstAndLastNameAndPostalCode(String firstName,String lastName,String postalCode){
        checkOutStepOne.fillFirstName(firstName).fillLastName(lastName).fillZipCode(postalCode).clickContinue();
        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html","All fields accept special character");
        softAssert.assertEquals(checkOutStepOne.isAlertPresent(),true,"  Alert not Found");
        softAssert.assertAll();
    }
}
