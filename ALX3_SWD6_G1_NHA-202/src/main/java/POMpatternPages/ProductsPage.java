package POMpatternPages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {
    WebDriver driver;

    // Locators
    By titleLocator = By.className("title");
    By firstProductButton = By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']");
    By secondProductButton = By.xpath("//button[@name='add-to-cart-sauce-labs-bike-light']");
    By cartIcon = By.className("shopping_cart_link");
    By cartItems = By.className("cart_item");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {


        return driver.findElement(titleLocator).getText();
    }

    public void addFirstProductToCart() {
        driver.findElement(firstProductButton).click();
    }

    public void addSecondProductToCart() {
        driver.findElement(secondProductButton).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }

    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }

}
