package POMpatternPages;

import Bot.ActionsBot;
import Bot.WaitBots;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutStepOne {


    private final WebDriver driver;
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

    //Constructor
    public CheckOutStepOne(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.waitbot = new WaitBots(driver);
        this.actionsBot = new ActionsBot(driver);
       this.loginPage=new LoginPage(driver);
       this.productsPage= new ProductsPage(driver);
        this.cartPage=new CartPage(driver);
    }

    //navigation method
    public void CartNavigation(String userName, String pass){
        loginPage.setLogin( userName, pass);
        productsPage.addFirstProductToCart();



    }





    public CheckOutStepOne fillFirstName(String firstName) {
        actionsBot.typing(firstNameLocator, firstName);
        return this;
    }

    public CheckOutStepOne fillLastName(String lastName) {
        actionsBot.typing(lastNameLocator, lastName);
        return this;
    }

    public CheckOutStepOne fillZipCode(String zipCode) {
        actionsBot.typing(zipCodeLocator, zipCode);
        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        actionsBot.clicking(continueButtonLocator);
        return new CheckoutOverviewPage(driver, wait);
    }
    public String missingDataAlertGetText() {
        String errorMessage =
                wait.until(ExpectedConditions.visibilityOfElementLocated(missingDataAlertLocator)).getText();
        return errorMessage;
    }

}