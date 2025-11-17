package Bot;

import org.openqa.selenium.*;
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

    // Actions
    public FluentWait<WebDriver> fluentWait(){
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(100))
                .ignoreAll(getExceptions());
    }

    private ArrayList<Class<? extends Exception>> getExceptions(){
        ArrayList<Class<? extends Exception >> exceptions= new ArrayList<>();
            exceptions.add(NoSuchElementException.class);
            exceptions.add(StaleElementReferenceException.class);
            exceptions.add(ElementClickInterceptedException.class);
            exceptions.add(ElementNotInteractableException.class);
            return exceptions;
    }






}
