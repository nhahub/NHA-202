package POMpatternPages;

import Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    WebDriver driver;
    WebDriverWait wait;
    // Locators
    private final By cartItem = By.className("cart_item");
    private final By cartItemName = By.className("inventory_item_name");
    private final By cartItemPrice = By.className("inventory_item_price");
    private final By quantityField = By.cssSelector(".cart_quantity");
    private final By checkoutBtn = By.id("checkout");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Methods
    public int getCartItemsCount() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItem));
        return driver.findElements(cartItem).size();
    }

    public String getFirstItemName() {
        List<WebElement> items = driver.findElements(cartItemName);
        return !items.isEmpty() ? items.getFirst().getText() : null;
    }

    public String getSecondItemName() {
        List<WebElement> items = driver.findElements(cartItemName);
        return items.size() > 1 ? items.get(1).getText() : null;
    }

    public String getFirstItemPrice() {
        List<WebElement> prices = driver.findElements(cartItemPrice);
        return !prices.isEmpty() ? prices.getFirst().getText() : null;
    }

    public String getSecondItemPrice() {
        List<WebElement> prices = driver.findElements(cartItemPrice);
        return prices.size() > 1 ? prices.get(1).getText() : null;
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

    //Suggested Method
    public void Navigate(WebDriver driver) {
        driver.get("https://www.saucedemo.com/");
// Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
// Add items to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        driver.findElement(By.className("shopping_cart_link")).click();
    }
}
