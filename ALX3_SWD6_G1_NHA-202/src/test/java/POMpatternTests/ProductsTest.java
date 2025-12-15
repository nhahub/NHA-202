package POMpatternTests;

import POMpatternPages.LoginPage;
import POMpatternPages.ProductsPage;
import CrossBrowserExecution.BaseTest;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productpage;

    @Override
    protected void differentSetupMethod() {
        // Initialize WebDriver and  productpage
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized --guest");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        productpage = new ProductsPage(driver);
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void testAddProductsToCart() {
        //step 1:login page
        loginPage.setLogin("standard_user", "secret_sauce");

        // Step 2: Verify Products are Displayed
        String title = productpage.getPageTitle();
        Assert.assertTrue(title.contains("Products"), "Products page is displayed");

        // Step 3: Add Products to Cart
        productpage.addFirstProductToCart();
        productpage.addSecondProductToCart();

        // Step 4: Go to Cart and Verify Total Number of Items in Cart
        productpage.goToCart();
        Assert.assertEquals(productpage.getCartItems().size(), 2, "Cart contains 2 items");

        System.out.println("Test passed: 2 items added to the cart.");
        driver.quit();
    }
}
