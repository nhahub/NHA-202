package POMpatternPages;

import Bot.ActionsBot;
import Bot.WaitBots;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckOutStepOne {

    //Instantiate a webdriver to control session actions
    private final WebDriver driver;

    //Define objects from Bot, waits, and some pages
    private ActionsBot actionsBot;
    private WaitBots waitbot;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;

    // Locators
    private final By firstNameLocator = By.id("first-name");
    private final By lastNameLocator = By.xpath("//*[@placeholder='Last Name']");
    private final By zipCodeLocator = By.cssSelector("[data-test=postalCode]");
    private final By continueButtonLocator = By.cssSelector("#continue");
    private final By missingDataAlertLocator = By.xpath("//*[@data-test='error']");
    private final By wrongDataTypeAlertLocator = By.xpath("//*[@data-test='error']");

    //Constructor
    public CheckOutStepOne(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.waitbot = new WaitBots(driver);
        this.actionsBot = new ActionsBot(driver);
    }

    //navigation method to cart
    public void CartNavigation(String userName, String pass){
        loginPage.setLogin(userName, pass);
        productsPage.addFirstProductToCart();
    }

    //This method is used to fill the 'First Name' field by passing it a string parameter
    //Also, it uses Fluent pattern to be able for method chaining
    public CheckOutStepOne fillFirstName(String firstName) {
        actionsBot.typing(firstNameLocator, firstName);
        return this;
    }

    //This method is used to fill the 'Last Name' field by passing it a string parameter
    //Also, it uses Fluent pattern to be able for method chaining
    public CheckOutStepOne fillLastName(String lastName) {
        actionsBot.typing(lastNameLocator, lastName);
        return this;
    }

    //This method is used to fill the 'Zip Code' field by passing it a string parameter
    //Also, it uses Fluent pattern to be able for method chaining
    public CheckOutStepOne fillZipCode(String zipCode) {
        actionsBot.typing(zipCodeLocator, zipCode);
        return this;
    }

    //This method is used to click 'Continue' button to proceed to 'Checkout Overview' page
    //Also, it uses Fluent pattern to be able for method chaining by returning the page again
    public CheckoutOverviewPage clickContinue() {
        actionsBot.clicking(continueButtonLocator);
        return new CheckoutOverviewPage(driver, wait);
    }

    //This method is used to return the text of the error message
    //Also, it uses Fluent pattern to be able for method chaining on this error message
    public String missingDataAlertGetText() {
        String errorMessage =
                wait.until(ExpectedConditions.visibilityOfElementLocated(missingDataAlertLocator)).getText();
        return errorMessage;
    }

    //This method is used to retrieve a list of elements if any, that its data type is error
    //It returns true if it found any error element on the page
    public boolean isAlertPresent(){
        List<WebElement> elements= driver.findElements(wrongDataTypeAlertLocator);
        return !elements.isEmpty();
    }

    //This method is used to navigate to 'Checkout Step one' page
    public void navigate(){
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

    }
}