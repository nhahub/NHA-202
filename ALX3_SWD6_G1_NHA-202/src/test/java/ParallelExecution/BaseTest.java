package ParallelExecution;

import POMpatternPages.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;

public abstract class BaseTest {
    protected WebDriver driver;

    //public WebDriver driver;
    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        //logs
        LogsManager.info("Starting Launching Browser ", browser);
        if (browser.equalsIgnoreCase("chrome")) {
            //logs
            System.setProperty("selenium.manager.supports-cdp", "false");
            System.setProperty("selenium.LOGGER.level", "OFF");
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-logging"});
            options.addArguments("--start-maximized");
            options.addArguments("--guest");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            //logs
            LogsManager.info("Starting Launching Browser, edge");
            System.setProperty("selenium.LOGGER.level", "OFF");
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--guest");
            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        differentSetupMethod();
    }
    protected void differentSetupMethod() {
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
