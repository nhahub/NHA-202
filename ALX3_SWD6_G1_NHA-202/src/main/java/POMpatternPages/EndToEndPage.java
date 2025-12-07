package POMpatternPages;

import Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EndToEndPage {
    WebDriver driver;
    String urlLogin = "https://www.saucedemo.com/";
    ActionsBot bot = new ActionsBot(driver);

    By firstProductButton = By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']");
    By secondProductButton = By.xpath("//button[@name='add-to-cart-sauce-labs-bike-light']");
    By shoppingCart = By.xpath("//a[@data-test='shopping-cart-link']");
    By checkouttButton = By.xpath("//button[@id='checkout']");
    By firstnameInput = By.xpath("//input[@name='firstName']");
    By lastnameInput = By.xpath("//input[@name='lastName']");
    By postalCodeInput = By.xpath("//input[@name='postalCode']");
    By continueButton = By.xpath("//input[@name='continue']");
    By finishButton = By.xpath("//button[@name='finish']");

    public void endToEndScenario(WebDriver driver) {
        //Navigates to login
        driver.navigate().to(urlLogin);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setLogin("standard_user", "secret_sauce");

        //Select Products
//        bot.clicking(firstProductButton);
//        bot.clicking(secondProductButton);
//        bot.clicking(shoppingCart);
        driver.findElement(firstProductButton).click();
        driver.findElement(secondProductButton).click();
        driver.findElement(shoppingCart).click();

        //Check Cart
//        bot.clicking(checkouttButton);
        driver.findElement(checkouttButton).click();

        //Fill info
//        bot.typing(firstnameInput, "Abdelrahman");
//        bot.typing(lastnameInput, "Shalaby");
//        bot.typing(postalCodeInput, "1234");
//        bot.clicking(continueButton);
        driver.findElement(firstnameInput).sendKeys("Abdelrahman");
        driver.findElement(lastnameInput).sendKeys("Shalaby");
        driver.findElement(postalCodeInput).sendKeys("1234");
        driver.findElement(continueButton).click();

        //Complete Checkout
//        bot.clicking(finishButton);
        driver.findElement(finishButton).click();
    }
}
