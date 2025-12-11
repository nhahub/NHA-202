package POMpatternTests;

import POMpatternPages.*;
import ParallelExecution.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutOverviewTest extends BaseTest{
   // WebDriver driver;
    Wait<WebDriver> wait;

    @Test
    public void testCalculatingPrices() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver, wait);
        checkoutOverviewPage.navigatelogin();
        //This method is used to assert that calculation of both items' prices is equal to items total
        Assert.assertEquals(
                checkoutOverviewPage.CalculateItemsTotal(),
                checkoutOverviewPage.FindItemsTotal());
        //This method is used to assert sum of items' prices and tax is equal to the final price
        Assert.assertEquals(
                checkoutOverviewPage.CalculateFinalPrice(),
                checkoutOverviewPage.FindFinalPrice());
    }
    @Test
    public void testEnsuringFirstItemNameWithBot() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver, wait);
        checkoutOverviewPage.navigatelogin();
        Assert.assertEquals(checkoutOverviewPage.getFirstItemNameWithBot(), checkoutOverviewPage.firstItemName);
    }
    @Test
    public void testEnsuringSecondItemNameWithBot() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver, wait);
        checkoutOverviewPage.navigatelogin();
        Assert.assertEquals(checkoutOverviewPage.getSecondItemNameWithBot(), checkoutOverviewPage.secondItemName);
    }
}
