package POMpatternTests;

import POMpatternPages.CheckoutOverviewPage;
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
        options.addArguments("--start-maximized --disable-notifications").setImplicitWaitTimeout(Duration.ofSeconds(10)); //--start-maximized --window-size=1920,1080 --incognito --disable-infobars --disable-notifications
        driver = new ChromeDriver(options);
    }

    @Test
    public void testCalculatingPrices(){
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver,wait);
        checkoutOverviewPage.navigate();
        Assert.assertEquals(checkoutOverviewPage.CalculateItemsTotal(),checkoutOverviewPage.FindItemsTotal());
        Assert.assertEquals(checkoutOverviewPage.CalculateFinalPrice(),checkoutOverviewPage.FindFinalPrice());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
