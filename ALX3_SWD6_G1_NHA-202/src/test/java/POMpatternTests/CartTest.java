package POMpatternTests;

import POMpatternPages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartTest {

    WebDriver driver;
    CartPage cart;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
// Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
// Add items to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        cart = new CartPage(driver);
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
        Assert.assertTrue(cart.isCheckoutButtonDisplayed(), "Checkout button should be visible");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
