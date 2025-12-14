package EndToEnd;

import POMpatternPages.*;
import CrossBrowserExecution.BaseTest;
import org.testng.annotations.Test;

public class EndToEnd extends BaseTest{
    EndToEndPage endToEndPage = new EndToEndPage();

    @Test
    public void endToEndScenarioTest() {
        endToEndPage.endToEndScenario(driver);
    }
}