package POMpatternPages;

import Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class CheckoutOverviewPage {
    //Instantiate a webdriver to control session actions
    WebDriver driver;

    //Initialise a wait
    Wait<WebDriver> wait;

    //Locators
    By firstItemPrice = By.xpath("//*[@id='checkout_summary_container']/div/div[1]/div[3]/div[2]/div[2]/div");
    By secondItemPrice = By.xpath("//*[@id='checkout_summary_container']/div/div[1]/div[4]/div[2]/div[2]/div");
    By itemsSubTotalPrice = By.xpath("//*[@class='summary_subtotal_label']");
    By tax = By.xpath("//*[@class='summary_tax_label']");
    By finalPrice = By.xpath("//*[@class='summary_total_label']");

    //Strings to be used from anywhere from other needed classes
    public String firstItemName = "Sauce Labs Backpack";
    public String secondItemName = "Sauce Labs Bike Light";
    String url = "https://www.saucedemo.com/checkout-step-two.html";

    //Constructor
    public CheckoutOverviewPage(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    //This method is used to complete the 4 steps before reaching 'Checkout Overview' page
    //The 4 steps are login with valid credentials, choose 2 products, checking the cart, and finally
    //filling the form in 'Checkout' first page
    public void navigatelogin() {

        //Navigates to login
        String urlLogin = "https://www.saucedemo.com/";
        this.driver.navigate().to(urlLogin);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.setLogin("standard_user", "secret_sauce");

        //Select Products
        By firstProductButton = By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']");
        By secondProductButton = By.xpath("//button[@name='add-to-cart-sauce-labs-bike-light']");
        By shoppingCart = By.xpath("//a[@data-test='shopping-cart-link']");
        this.driver.findElement(firstProductButton).click();
        this.driver.findElement(secondProductButton).click();
        this.driver.findElement(shoppingCart).click();

        //Check Cart
        By checkouttButton = By.xpath("//button[@id='checkout']");
        this.driver.findElement(checkouttButton).click();

        //Fill info
        By firstnameInput = By.xpath("//input[@name='firstName']");
        By lastnameInput = By.xpath("//input[@name='lastName']");
        By postalCodeInput = By.xpath("//input[@name='postalCode']");
        By continueButton = By.xpath("//input[@name='continue']");
        this.driver.findElement(firstnameInput).sendKeys("Abdelrahman");
        this.driver.findElement(lastnameInput).sendKeys("Shalaby");
        this.driver.findElement(postalCodeInput).sendKeys("1234");
        this.driver.findElement(continueButton).click();
        //this.driver.navigate().to(url);
    }

    //This method is used to find the total price calculated by the website and store it in a double
    public double FindItemsTotal() {
        return Double.parseDouble(driver.findElement(itemsSubTotalPrice).getText().substring(13, 18));
    }

    //This method is used to find the tax calculated by the website
    public double FindTax() {
        return Double.parseDouble(driver.findElement(tax).getText().substring(6, 10));
    }

    //This method is used to find the final price (Total + Tax) calculated by the website
    public double FindFinalPrice() {
        return Double.parseDouble(driver.findElement(finalPrice).getText().substring(8, 13));
    }

    //This method is used to calculate the total price manually by adding the price of the 2 items together
    public double CalculateItemsTotal() {
        double priceOfFirstItem = Double.parseDouble(driver.findElement(firstItemPrice).getText().substring(1, 6));
        double priceOfSecondItem = Double.parseDouble(driver.findElement(secondItemPrice).getText().substring(1, 5));
        return priceOfFirstItem + priceOfSecondItem;
    }

    //This method is used to calculate final price manually by adding total price and tax
    public double CalculateFinalPrice() {
        return CalculateItemsTotal() + FindTax();
    }

    //This method is used to get the first item name using Bot
    public String getFirstItemNameWithBot() {
        ActionsBot actionsBot = new ActionsBot(driver);
        By firstItemName = By.xpath("//*[@id='item_4_title_link']/div");
        return actionsBot.getText(firstItemName);
    }

    //This method is used to get the first item name using Bot
    public String getSecondItemNameWithBot() {
        ActionsBot actionsBot = new ActionsBot(driver);
        By secondItemName = By.xpath("//*[@id='item_0_title_link']/div");
        return actionsBot.getText(secondItemName);
    }

    //This method is used to click 'Finish' button at the end to complete the process
    public void clickFinish(){
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("finish")))).click();

    }
    //This method is used to get the current url of the page
    public String checkOutGetUrl(){
        return driver.getCurrentUrl();
    }
}
