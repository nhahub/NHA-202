package POMpatternTests;

import POMpatternPages.CartPage;
import CrossBrowserExecution.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

//    WebDriver driver;
    CartPage cart;

    //This method is overridden from the BaseTest to add additional setup
@Override
protected void differentSetupMethod(){

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

    //This method is used to verify that the 'Cart' contains 2 items
    @Test
    public void verifyTotalItemsInCart() {
        Assert.assertEquals(cart.getCartItemsCount(), 2, "Cart should have 2 items");
    }

    //This method is used to verify the names of the 2 items selected from products match expected names
    @Test
    public void verifyProductsInCart() {
        Assert.assertEquals(cart.getFirstItemName(), "Sauce Labs Backpack");
        Assert.assertEquals(cart.getSecondItemName(), "Sauce Labs Bike Light");
    }

    //This method is used to verify the prices and quantities of the 2 products selected
    @Test
    public void verifyProductInfo() {
        Assert.assertEquals(cart.getFirstItemPrice(), "$29.99");
        Assert.assertEquals(cart.getSecondItemPrice(), "$9.99");

        Assert.assertEquals(cart.getFirstItemQuantity(), 1, "First item quantity should be 1");
        Assert.assertEquals(cart.getSecondItemQuantity(), 1, "Second item quantity should be 1");
    }

    //This method is used to ensure that 'Checkout' button is displayed
    @Test
    public void verifyCheckoutButton() {
        Assert.assertTrue(cart.isCheckoutButtonDisplayed(), "Checkout button should be visible");
    }
}
