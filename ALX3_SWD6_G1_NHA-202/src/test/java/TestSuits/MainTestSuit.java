package TestSuits;

import POMpatternTests.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class MainTestSuit {
    LoginTest loginTest = new LoginTest();
    ProductsTest productsTest = new ProductsTest();
    CartTest cartTest = new CartTest();
    CheckoutOverviewTest checkoutOverviewTest = new CheckoutOverviewTest();




}