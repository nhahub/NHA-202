package POMpatternPages;


import Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private ActionsBot actionsBot;

    // Locators
    By titleLocator = By.className("title");
    By firstProductButton = By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']");
    By secondProductButton = By.xpath("//button[@name='add-to-cart-sauce-labs-bike-light']");
    By cartIcon = By.className("shopping_cart_link");
    By cartItems = By.className("cart_item");

    // Constructor
    public ProductsPage (WebDriver driver) {
        this.driver = driver;
       this.actionsBot = new ActionsBot(driver);
    }

    //This method is used to add the first item to cart
    public  ProductsPage addFirstProductToCart() {
        actionsBot.clicking(firstProductButton);
        return this;

    }

    //This method is used to add the second item to cart
    public  ProductsPage addSecondProductToCart() {
        actionsBot.clicking(secondProductButton);
        return this;
    }

    //This method is used to get the 'Title' text
    public String getPageTitle() {
        System.out.println("Products page are displayed");
        return driver.findElement(titleLocator).getText();

    }

    //This method is used to navigate to 'Cart' page from 'Products' page
    public void goToCart() {
        actionsBot.clicking(cartIcon);
    }

    //This method is used to retrieve the items in the cart
    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }
}
