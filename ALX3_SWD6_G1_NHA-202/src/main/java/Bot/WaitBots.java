package Bot;

import org.openqa.selenium.*;
import org.openqa.selenium.net.UrlChecker;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public class WaitBots {

    private WebDriver driver;

    // constructor
    public WaitBots(WebDriver driver){
        this.driver= driver;
    }

//    ************************************Actions*********************************************

    //This method handles wait by using FluentWait, it uses duration of 10 seconds, and try every 0.1 seconds
    public FluentWait<WebDriver> fluentWait(){
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoreAll(getExceptions());
    }

    //This method extends abstract class Exception to ignor the added exceptions to it
    private ArrayList<Class<? extends Exception>> getExceptions(){
        ArrayList<Class<? extends Exception >> exceptions= new ArrayList<>();
            exceptions.add(NoSuchElementException.class);
            exceptions.add(StaleElementReferenceException.class);
            exceptions.add(ElementClickInterceptedException.class);
            exceptions.add(ElementNotInteractableException.class);
            return exceptions;
    }
}
