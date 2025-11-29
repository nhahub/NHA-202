package POMpatternPages;

import Bot.ActionsBot;
import Bot.WaitBots;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutStepOne {
    private WebDriver driver;
    private ActionsBot actionsBot;
    private WebDriverWait wait;

    // Locators
    private By firstName= By.id("first-name");
    private By lastName= By.xpath("//*[@placeholder='Last Name']");
    private By zipCode= By.cssSelector("[data-test=postalCode]");
    private By continueButton=  By.cssSelector("#continue");


    public CheckOutStepOne ( WebDriver driver){
        this.driver=driver;
        this.actionsBot = new ActionsBot(driver);
    }


    public CheckOutStepOne fillFirstName(String userFirstName){
        actionsBot.typing(firstName,userFirstName);
    return this;}
    public CheckOutStepOne secondName(String userSecondName){
        actionsBot.typing(lastName,userSecondName);
        return this;}
    public CheckOutStepOne fillzipCode(String userZipCode){
        actionsBot.typing(zipCode,userZipCode);
        return this;}

    public CheckoutOverviewPage navigationalCheckOut(){
        actionsBot.clicking(continueButton);
        return new CheckoutOverviewPage(driver, wait);

    }


}