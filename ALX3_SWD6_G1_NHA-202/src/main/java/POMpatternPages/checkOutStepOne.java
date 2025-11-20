package POMpatternPages;

import Bot.ActionsBot;
import Bot.WaitBots;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class checkOutStepOne {
    private WebDriver driver;
    private ActionsBot actionsBot;
    private WaitBots waitBots;

    // Locators
    private By firstName= By.id("first-name");
    private By lastName= By.xpath("//*[@placeholder='Last Name']");
    private By zipCode= By.cssSelector("[data-test=postalCode]");
    private By continueButton=  By.cssSelector("#continue");

    public checkOutStepOne ( WebDriver driver){
        this.driver=driver;
    }


    public CheckoutOverviewPage fillYourData(,){
        actionsBot.typing(firstName,"standard_user");
        actionsBot.typing(lastName,"secret_sauce");
        actionsBot.typing(zipCode,"02456");
        actionsBot.clicking(continueButton);
        return new CheckoutOverviewPage (driver);

    }


}