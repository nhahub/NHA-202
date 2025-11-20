package POMpatternTests;

import POMpatternPages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ProductsTest {

    WebDriver driver;
    ProductsPage productpage;


    @BeforeMethod
    public void setUp() {

        // Initialize WebDriver and  productpage
        driver = new ChromeDriver();
        productpage = new ProductsPage(driver);
        driver.navigate().to("https://www.saucedemo.com/inventory.html");

    }

    @Test
    public void testAddProductsToCart() {
        // Step 1: Verify Products are Displayed
        String title = productpage.getPageTitle();
        Assert.assertTrue(title.contains("Products"), "Page title does not contain 'Products'");

        // Step 2: Add Products to Cart
        productpage.addFirstProductToCart();
        productpage.addSecondProductToCart();

        // Step 3: Go to Cart and Verify Total Number of Items in Cart
        productpage.goToCart();
        Assert.assertEquals(productpage.getCartItems().size(), 2, "Cart does not contain 2 items");


        System.out.println("Test passed: 2 items added to the cart.");

    }


    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

}
