package POMpatternPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    //Instantiate a webdriver to control session actions
    WebDriver driver;

    //Initialise a wait
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

    //**************************************** Methods ******************************************

    //This method is used to calculates the number of items in the cart
    public int getCartItemsCount() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItem));
        return driver.findElements(cartItem).size();
    }

    //This method is used to get the first item name from the items in the cart
    public String getFirstItemName() {
        List<WebElement> items = driver.findElements(cartItemName);
        return !items.isEmpty() ? items.getFirst().getText() : null;
    }

    //This method is used to get the second item name from the items in the cart
    public String getSecondItemName() {
        List<WebElement> items = driver.findElements(cartItemName);
        return items.size() > 1 ? items.get(1).getText() : null;
    }

    //This method is used to get the first item price from the items in the cart
    public String getFirstItemPrice() {
        List<WebElement> prices = driver.findElements(cartItemPrice);
        return !prices.isEmpty() ? prices.getFirst().getText() : null;
    }

    //This method is used to get the second item price from the items in the cart
    public String getSecondItemPrice() {
        List<WebElement> prices = driver.findElements(cartItemPrice);
        return prices.size() > 1 ? prices.get(1).getText() : null;
    }

    //This method is used to get the first item quantity from the items in the cart
    public int getFirstItemQuantity() {
        List<WebElement> quantities = driver.findElements(quantityField);
        return !quantities.isEmpty() ? Integer.parseInt(quantities.getFirst().getText()) : 0;
    }

    //This method is used to get the second item quantity from the items in the cart
    public int getSecondItemQuantity() {
        List<WebElement> quantities = driver.findElements(quantityField);
        return quantities.size() > 1 ? Integer.parseInt(quantities.get(1).getText()) : 0;
    }

    //This method is used to check if the 'Checkout' button is displayed
    public boolean isCheckoutButtonDisplayed() {
        return driver.findElement(checkoutBtn).isDisplayed();
    }

    //This method is wait the 'Checkout' button to be displayed then click on it
    public void clickCheckButton(){wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    //This method is used at first to login then choose 2 items and add them to the cart
    //then clicks the cart icon
    public void navigate (WebDriver driver) {
        driver.get("https://www.saucedemo.com/");
// Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
// Add items to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }
}
