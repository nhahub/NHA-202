package EndToEnd;

import POMpatternPages.*;
import CrossBrowserExecution.BaseTest;
import org.testng.annotations.Test;

public class EndToEnd extends BaseTest{
    EndToEndPage endToEndPage = new EndToEndPage();

    //This method is used to perform the 'End to End' approach
    @Test
    public void endToEndScenarioTest() {
        endToEndPage.endToEndScenario(driver);
    }
}