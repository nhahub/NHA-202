package POMpatternTests;

import POMpatternPages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class EndToEnd {

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckOutStepOne checkOutStepOne;
    CheckoutOverviewPage checkoutOverviewPage;
    WebDriverWait wait;
    SoftAssert softAssert;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        softAssert = new SoftAssert();
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        checkOutStepOne = new CheckOutStepOne(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver,wait);
        driver.get("https://www.saucedemo.com/");
    }
    @Test(dataProvider = "loginInvalidData",dataProviderClass = TestData.class,priority = 1)
    public void loginUsingInvalidCredentials(String userName, String password){
        new LoginPage(driver)
                .setLogin(userName, password)
                .validation("https://www.saucedemo.com/");}
    @Test(dataProvider = "loginValidData",dataProviderClass = TestData.class, priority = 2)
    public void loginUsingValidCredentials(String userName, String password){
        new LoginPage(driver)
                .setLogin(userName,password)
                .validation("https://www.saucedemo.com/inventory.html");
    }
    @Test(priority = 3)
    public void testAddProductsToCart() {

        //step 1:login page
        loginPage.setLogin("standard_user", "secret_sauce");

        // Step 2: Verify Products are Displayed
        String title = productsPage.getPageTitle();
        Assert.assertTrue(title.contains("Products"), "Products page is displayed");

        // Step 3: Add Products to Cart
        productsPage.addFirstProductToCart();
        productsPage.addSecondProductToCart();

        // Step 4: Go to Cart and Verify Total Number of Items in Cart
        productsPage.goToCart();
        Assert.assertEquals(productsPage.getCartItems().size(), 2, "Cart contains 2 items");
        System.out.println("Test passed: 2 items added to the cart.");

    }
    @Test(priority = 4)
    public void verifyTotalItemsInCart() {
        Assert.assertEquals(cartPage.getCartItemsCount(), 2, "Cart should have 2 items");
    }

    @Test(priority = 5)
    public void verifyProductsInCart() {
        Assert.assertEquals(cartPage.getFirstItemName(), "Sauce Labs Backpack");
        Assert.assertEquals(cartPage.getSecondItemName(), "Sauce Labs Bike Light");
    }

    @Test(priority = 6)
    public void verifyProductInfo() {
        Assert.assertEquals(cartPage.getFirstItemPrice(), "$29.99");
        Assert.assertEquals(cartPage.getSecondItemPrice(), "$9.99");

        Assert.assertEquals(cartPage.getFirstItemQuantity(), 1, "First item quantity should be 1");
        Assert.assertEquals(cartPage.getSecondItemQuantity(), 1, "Second item quantity should be 1");
    }

    @Test(priority = 7)
    public void verifyCheckoutButton() {
        Assert.assertTrue(cartPage.isCheckoutButtonDisplayed(), "Checkout button should be visible");
        cartPage.clickCheckButton();
    }
    @Test(priority = 8)
    public void validLogInCredentialsInCheckOutStepOneTC5() {

        String pageTitle = new CheckOutStepOne(driver).fillFirstName("Abdelrahman").fillLastName("Shalaby").fillZipCode("03").clickContinue().checkOutGetUrl();
        Assert.assertEquals(pageTitle,"https://www.saucedemo.com/checkout-step-two.html");
    }

    @Test(dataProvider="CheckOutStepOneMissingField" ,dataProviderClass = TestData.class,priority = 9)
    public void fillOneMissingField(String firstName,String lastName,String postalCode, String expectedErrorMessage){
        checkOutStepOne.fillFirstName(firstName).fillLastName(lastName).fillZipCode(postalCode).clickContinue();
        String actualError= checkOutStepOne.missingDataAlertGetText();
        Assert.assertEquals(actualError,expectedErrorMessage);
        System.out.println(" all is good ");
    }
    @Test(dataProvider ="numbersAndSpecialCharacter", dataProviderClass = TestData.class,priority = 10)
    public void UsingSpecialCharacterInFirstAndLastNameAndPostalCode(String firstName,String lastName,String postalCode){
        checkOutStepOne.fillFirstName(firstName).fillLastName(lastName).fillZipCode(postalCode).clickContinue();
        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/checkout-step-one.html","All fields accept special character");
        softAssert.assertEquals(checkOutStepOne.isAlertPresent(),true,"  Alert not Found");
        softAssert.assertAll();

    }

}
