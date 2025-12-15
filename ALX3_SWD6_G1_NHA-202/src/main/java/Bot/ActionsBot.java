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

//    ************************************Actions*********************************************

    //    This method is used for perform the function of 'clicking' from any class in the project
    //    uses object from ActionsBot, and also the method itself handles wait from the WaitBots
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

    //    This method is used for perform the function of 'typing' from any class in the project
    //    uses object from ActionsBot, and also the method itself handles wait from the WaitBots
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

    //    This method is used for perform the function of 'getting text' from any class in the project
    //    uses object from ActionsBot, and also the method itself handles wait from the WaitBots
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

