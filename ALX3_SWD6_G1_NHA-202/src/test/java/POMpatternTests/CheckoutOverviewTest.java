package POMpatternTests;

import POMpatternPages.CheckoutOverviewPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutOverviewTest {
    WebDriver driver;
    Wait<WebDriver> wait;
    ChromeOptions options = new ChromeOptions();

    @BeforeMethod
    public void setup() {
        //Examples of arguments that can be added to AddArguments method for ChromeOptions
        // --start-maximized --window-size=1920,1080 --incognito
        // --disable-infobars --disable-notifications
        // --disable-notifications --profile.password_manager_leak_detection
        // --credentials_enable_service --profile.password_manager_enabled"
        options.addArguments("--start-maximized --guest");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testCalculatingPrices() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver, wait);

        checkoutOverviewPage.navigate();

        //This method is used to assert that calculation of both items' prices is equal to items total
        Assert.assertEquals(
                checkoutOverviewPage.CalculateItemsTotal(),
                checkoutOverviewPage.FindItemsTotal());

        //This method is used to assert sum of items' prices and tax is equal to the final price
        Assert.assertEquals(
                checkoutOverviewPage.CalculateFinalPrice(),
                checkoutOverviewPage.FindFinalPrice());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
