package POMpatternTests;

import POMpatternPages.CartPage;
import POMpatternPages.CheckOutStepOne;
import POMpatternPages.LoginPage;
import POMpatternPages.ProductsPage;
import CrossBrowserExecution.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckOutStepOneTest extends BaseTest {
//    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckOutStepOne checkOutStepOne;
    SoftAssert softAssert=new SoftAssert();

    //This method is overridden from the BaseTest to add additional setup
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

    /*
    This method is used to check that the url is changed to the 'Checkout Overview' page
     when fill all required fields
    This method is use 'Fluent' pattern to make method chaining
    */
    @Test
    public void validLogInCredentialsInCheckOutStepOneTC5() {
        String pageTitle = new CheckOutStepOne(driver).fillFirstName("Abdelrahman").fillLastName("Shalaby").fillZipCode("03").clickContinue().checkOutGetUrl();
        Assert.assertEquals(pageTitle,"https://www.saucedemo.com/checkout-step-two.html");
    }

    /*
    This method is used to check the error message appears when entering invalid data in the fields
    This method is use data provider to pull invalid credentials even if it is missing or invalid,
    then compare the error message displayed on the screen with the expected one in the data provider
    This method is use 'Fluent' pattern to make method chaining
    */
    @Test(dataProvider="CheckOutStepOneMissingField" ,dataProviderClass = TestData.class)
    public void fillOneMissingField(String firstName,String lastName,String postalCode, String expectedErrorMessage){
        checkOutStepOne.fillFirstName(firstName).fillLastName(lastName).fillZipCode(postalCode).clickContinue();
        String actualError= checkOutStepOne.missingDataAlertGetText();
        Assert.assertEquals(actualError,expectedErrorMessage);
        System.out.println(" all is good ");
    }


    /*
    This method uses soft assertion to test several assertion at once for the same condition,
    first assertion is that website will not direct to next page,when using special character in firstname,
    lastname and zipcode,
    Second assertion is expected error message telling user "special character not allowed in these fields"
    Note: the site contains that bug, and it actually accepts special characters in all fields & alert is not
    shawn, so the method is intended to be failed to show that there is a bug
    */
    @Test(dataProvider ="numbersAndSpecialCharacter", dataProviderClass = TestData.class)
    public void UsingSpecialCharacterInFirstAndLastNameAndPostalCode(String firstName,String lastName,String postalCode){
        checkOutStepOne.fillFirstName(firstName).fillLastName(lastName).fillZipCode(postalCode).clickContinue();
        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html","All fields accept special character");
        softAssert.assertEquals(checkOutStepOne.isAlertPresent(),true,"  Alert not Found");
        softAssert.assertAll();
    }
}
