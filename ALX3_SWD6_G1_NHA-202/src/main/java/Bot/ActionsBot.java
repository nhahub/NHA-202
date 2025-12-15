package Bot;

import POMpatternPages.LogsManager;
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
  //logs
    LogsManager.info("Clicking on element: " + locator);
        waitBots.fluentWait().until(d -> {
                    try {
                        WebElement element = d.findElement(locator);
  //logs
    LogsManager.debug("Element found, Scrolling into view: " + locator);
                        new Actions(d).scrollToElement(element);
                        element.click();
   //logs
     LogsManager.info("Clicked Successfully on element: " + locator);

                        return true;
                    } catch (Exception e) {
    //logs
      LogsManager.warn("Retry clicking on element: " + locator,  "e.getMessage()");
                        return false;
                    }
                }
        );
    }

    //    This method is used for perform the function of 'typing' from any class in the project
    //    uses object from ActionsBot, and also the method itself handles wait from the WaitBots

    public void typing(By locator, String text) {
      //logs
            LogsManager.info("Attempting to type into element:" + locator);

        waitBots.fluentWait().until(d -> {
                    try {
                        WebElement element = d.findElement(locator);
                        new Actions(d).scrollToElement(element);
                        element.clear();
       //logs
             LogsManager.debug("Cleared text field:" + locator);

                        element.sendKeys(text);
       //logs
              LogsManager.info("Typing action completed on element:  " + locator);

                        return true;
                    } catch (Exception e) {
        //logs
              LogsManager.warn("Retry typing into element: " + locator , e.getMessage());

                        return false;
                    }
                }
        );


    }

    //    This method is used for perform the function of 'getting text' from any class in the project
    //    uses object from ActionsBot, and also the method itself handles wait from the WaitBots

    public String getText(By locator) {
//logs
    LogsManager.info("Attempting to get text from element: " + locator);

        return waitBots.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        new Actions(d).scrollToElement(element);
                        String textMsg = element.getText();
                        if (!textMsg.isEmpty()) {
 //logs
    LogsManager.info("Text retrieved from element: " + locator , textMsg);
                            return textMsg;
                        } else {
//logs
    LogsManager.info("Text is empty for element: " + locator);
                            return null;
                        }
                    } catch (Exception e) {
//logs
    LogsManager.error("Failed to get text from element: " + locator , e.getMessage());
                        return null;
                    }
                }
        );


    }

}

