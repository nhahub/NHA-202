package POMpatternTests;

import POMpatternPages.LoginPage;
import CrossBrowserExecution.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

//    private WebDriver driver;
@Override
protected void differentSetupMethod(){
    driver.get("https://www.saucedemo.com/");
}

    //Login with Acceptance username and password
    @Test(dataProvider = "loginValidData",dataProviderClass = TestData.class)
    public void loginUsingValidCredentials(String userName, String password){
       new LoginPage(driver)
               .setLogin(userName,password)
               .validation("https://www.saucedemo.com/inventory.html");
    }

    //invalid name and valid password
    @Test(dataProvider = "loginInvalidData",dataProviderClass = TestData.class)
    public void loginUsingInvalidCredentials(String userName, String password){
         new LoginPage(driver)
                .setLogin(userName, password)
             .validation("https://www.saucedemo.com/");
    }
}
