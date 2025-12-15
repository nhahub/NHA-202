package POMpatternPages;

import Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage {

    // variables
    private final WebDriver driver;
    private ActionsBot actionsBot;
    String CurrentUrl;

    //locators
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.xpath("//input[@type='submit']");

    // constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.actionsBot = new ActionsBot(driver);
    }

    // Actions
    //This method is used for login process by passing 'username' and 'password' as parameters
    //Also, it provides a logging message in the console contains username and password as a group of stars
    public LoginPage setLogin(String userName, String pass) {

        //logs
        LogsManager.info("Starting Login Test with Username: ", userName + " ", ", Password:" + " " + "**[SENSITIVE DATA HIDDEN]**");

        actionsBot.typing(username, userName);
        actionsBot.typing(password, pass);
        actionsBot.clicking(loginButton);
        return this;
    }

    // validation
    public ProductsPage validation(String expectedUrl) {
        //logs
        LogsManager.debug("Expected Url after login:" + expectedUrl);
        LogsManager.debug("String CurrentUrl:" + driver.getCurrentUrl());
        //validation
        try {
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
            LogsManager.info("Assertion Passed : , User reached the Corrected Url");
        } catch (AssertionError e) {
            LogsManager.error("Assertion FAILED! URl mismatch. + Expected:" + expectedUrl + "But Found:" + CurrentUrl);
            throw e;
        }
        return null;

    }
}
