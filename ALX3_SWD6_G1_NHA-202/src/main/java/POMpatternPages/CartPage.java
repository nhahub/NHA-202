package POMpatternPages;

import Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final ProductsPage productsPage;
    private final ActionsBot actionsBot;


    // Locators
    private final By cartItem = By.className("cart_item");
    private final By cartItemName = By.className("inventory_item_name");
    private final By cartItemPrice = By.className("inventory_item_price");
    private final By quantityField = By.cssSelector(".cart_quantity");
    private final By checkoutBtn = By.xpath("//*[@id='checkout']");
    By cartIcon = By.className("shopping_cart_link");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.productsPage = new ProductsPage(driver);
        this.actionsBot = new ActionsBot(driver);

    }

    // Methods
    public void navigateToCart(String userName, String pass) {
        loginPage.setLogin(userName, pass);
        productsPage.addFirstProductToCart();
        productsPage.addSecondProductToCart();
        productsPage.goToCart();
    }


    public int getCartItemsCount() {
        actionsBot.getText(cartItem);
        return driver.findElements(cartItem).size();


    }

    public String getFirstItemName() {
        actionsBot.getText(cartItemName);
        return driver.findElements(cartItemName).get(0).getText();

    }


    public String getSecondItemName() {
        actionsBot.getText(cartItemName);
        return driver.findElements(cartItemName).get(1).getText();

    }

    public String getFirstItemPrice() {
        actionsBot.getText(cartItemPrice);
        //return driver.findElement(cartItemPrice).getText();
        return driver.findElements(cartItemPrice).get(0).getText();
    }

    public String getSecondItemPrice() {
        actionsBot.getText(cartItemPrice);
        //return driver.findElement(cartItemPrice).getText();
        return driver.findElements(cartItemPrice).get(1).getText();
    }

    public int getFirstItemQuantity() {
        List<WebElement> quantities = driver.findElements(quantityField);
        return !quantities.isEmpty() ? Integer.parseInt(quantities.getFirst().getText()) : 0;
    }

    public int getSecondItemQuantity() {
        List<WebElement> quantities = driver.findElements(quantityField);
        return quantities.size() > 1 ? Integer.parseInt(quantities.get(1).getText()) : 0;
    }


    public boolean isCheckoutButtonDisplayed() {
        return driver.findElement(checkoutBtn).isDisplayed();
    }

    public void clickCheckoutButton() {
        driver.findElement(checkoutBtn).click();
    }


    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}