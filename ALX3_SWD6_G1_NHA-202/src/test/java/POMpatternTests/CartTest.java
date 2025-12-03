package POMpatternTests;

import POMpatternPages.CartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartTest {

    WebDriver driver;
    CartPage cart;
    ChromeOptions options = new ChromeOptions();

    @BeforeClass
    public void setup() {
//        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized --guest");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        cart = new CartPage(driver);
        cart.navigateToCart("standard_user", "secret_sauce");
    }

    // Tests
    @Test
    public void verifyTotalItemsInCart() {
        Assert.assertEquals(cart.getCartItemsCount(), 2, "Cart should have 2 items");
    }

    @Test
    public void verifyProductsInCart() {
        Assert.assertEquals(cart.getFirstItemName(), "Sauce Labs Backpack");
        Assert.assertEquals(cart.getSecondItemName(), "Sauce Labs Bike Light");
    }

    @Test
    public void verifyProductInfo() {
        Assert.assertEquals(cart.getFirstItemPrice(), "$29.99");
        Assert.assertEquals(cart.getSecondItemPrice(), "$9.99");

        Assert.assertEquals(cart.getFirstItemQuantity(), 1, "First item quantity should be 1");
        Assert.assertEquals(cart.getSecondItemQuantity(), 1, "Second item quantity should be 1");
    }

    @Test
    public void verifyCheckoutButton() {
        Assert.assertTrue(cart.isCheckoutButtonDisplayed(), "Checkout button should be displayed");
    }

    @Test
    public void proceedToCheckout() {
        cart.clickCheckoutButton();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one.html"),
                "Should navigate to checkout step one page");
        cart.goToCart();
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
