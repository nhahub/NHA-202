package Bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActionsBot {

    private WebDriver driver;

    //locators
    private ActionsBot actionsBot;
    private WaitBots waitBots;


    // constructors
    public ActionsBot (WebDriver driver){
        this.driver=driver;
        this.waitBots=new WaitBots(driver);

    }


    // Actions
     public void clicking(By locator){

     }



    public void typing(By locator, String text){

    }


    public String getText(By locator) {
        return null;
    }



}
