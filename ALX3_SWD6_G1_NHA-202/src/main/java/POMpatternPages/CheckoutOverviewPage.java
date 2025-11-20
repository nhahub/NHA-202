package POMpatternPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;


public class CheckoutOverviewPage {
    WebDriver driver;
    Wait<WebDriver> wait;

    // By firstItemPrice = By.xpath("//div[3]//text()[2]");
    // By secondItemPrice = By.xpath("//div[4]//text()[2]");
    // By itemsTotalPrice = By.xpath("//div[6]/text()[2]");
    // By tax = By.xpath("//div[7]/text()[2]");
    // By finalPrice = By.xpath("//div[8]/text()[2]");
    //By firstItemPrice = By.xpath("//*[@id='checkout_summary_container']/div/div[1]/div[3]/div[2]/div[2]/div");
    //By secondItemPrice = By.xpath("//*[@id='checkout_summary_container']/div/div[1]/div[4]/div[2]/div[2]/div");
    //By firstItemPrice = By.xpath("//div[3]/[@class='inventory_item_price']");
    By firstItemPrice = By.xpath("//*[@id='checkout_summary_container']/div/div[1]/div[3]/div[2]/div[2]/div");
    By secondItemPrice = By.xpath("//*[@id='checkout_summary_container']/div/div[1]/div[4]/div[2]/div[2]/div");
    By itemsSubTotalPrice = By.xpath("//*[@class='summary_subtotal_label']");
    By tax = By.xpath("//*[@class='summary_tax_label']");
    By finalPrice = By.xpath("//*[@class='summary_total_label']");


    String url = "https://www.saucedemo.com/checkout-step-two.html";

    public CheckoutOverviewPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigate() {
        //Navigates to login
        String urlLogin = "https://www.saucedemo.com/";
        this.driver.navigate().to(urlLogin);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.setLogin("standard_user", "secret_sauce");

        //CompleteProducts
        By firstProductButton = By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']");
        By secondProductButton = By.xpath("//button[@name='add-to-cart-sauce-labs-bike-light']");
        this.driver.findElement(firstProductButton).click();
        this.driver.findElement(secondProductButton).click();
        By shoppingCart = By.xpath("//a[@data-test='shopping-cart-link']");
        this.driver.findElement(shoppingCart).click();

        //Check Cart
        By checkouttButton = By.xpath("//button[@id='checkout']");
        this.driver.findElement(checkouttButton).click();

        //Fill info
        By firstnameInput = By.xpath("//input[@name='firstName']");
        By lastnameInput = By.xpath("//input[@name='lastName']");
        By postalCodeInput = By.xpath("//input[@name='postalCode']");
        By continueButton = By.xpath("//input[@name='continue']");
        this.driver.findElement(firstnameInput).sendKeys("First");
        this.driver.findElement(lastnameInput).sendKeys("Last");
        this.driver.findElement(postalCodeInput).sendKeys("1234");
        this.driver.findElement(continueButton).click();
        //this.driver.navigate().to(url);
    }

    public double FindItemsTotal() {
        return Double.parseDouble(driver.findElement(itemsSubTotalPrice).getText().substring(13,18));
    }

    public double FindTax() {
        return Double.parseDouble(driver.findElement(tax).getText().substring(6,10));
    }

    public double FindFinalPrice() {
        return Double.parseDouble(driver.findElement(finalPrice).getText().substring(8,13));
    }

    public double CalculateItemsTotal() {
        double priceOfFirstItem = Double.parseDouble(driver.findElement(firstItemPrice).getText().substring(1,6));
        double priceOfSecondItem = Double.parseDouble(driver.findElement(secondItemPrice).getText().substring(1,5));
        return priceOfFirstItem + priceOfSecondItem;
    }

    public String checkOutGetUrl(){
        return driver.getCurrentUrl();
    }
    public double CalculateFinalPrice() {
        return CalculateItemsTotal() + FindTax();
    }


//    public double TemporaryMethod() {
//        double priceOfFirstItem = Double.parseDouble(driver.findElement(firstItemPrice).toString());
//        double priceOfSecondItem = Double.parseDouble(driver.findElement(secondItemPrice).toString());
//        double priceOfItemsTotal = Double.parseDouble(driver.findElement(itemsTotalPrice).toString());
//        double addedTax = Double.parseDouble(driver.findElement(tax).toString());
//        double priceOfFinalTotal = Double.parseDouble(driver.findElement(finalPrice).toString());
//
//        double
//        return
//    }


}
