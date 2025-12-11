package Bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsBot {

    private WebDriver driver;

    //locators
    private ActionsBot actionsBot;
    private WaitBots waitBots;

    // constructors
    public ActionsBot(WebDriver driver) {
        this.driver = driver;
        this.waitBots = new WaitBots(driver);
    }

    // Actions
    public void clicking(By locator) {

        waitBots.fluentWait().until(d -> {
                    try {
                        WebElement element = d.findElement(locator);
                        new Actions(d).scrollToElement(element);
                        element.click();

                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }


    public void typing(By locator, String text) {

        waitBots.fluentWait().until(d -> {
                    try {
                        WebElement element = d.findElement(locator);
                        new Actions(d).scrollToElement(element);
                        element.clear();
                        element.sendKeys(text);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );


    }


    public String getText(By locator) {

       return waitBots.fluentWait().until(d ->
       {
            try {
                WebElement element = d.findElement(locator);
                new Actions(d).scrollToElement(element);
                String textMsg = element.getText();
                if (!textMsg.isEmpty()) {
                    return textMsg;
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        }
     );


    }

}

