package EndToEnd;

import POMpatternPages.*;
import ParallelExecution.BaseTest;
import org.testng.annotations.Test;

public class EndToEnd extends BaseTest{
    EndToEndPage endToEndPage = new EndToEndPage(driver);

    @Test
    public void endToEndScenarioTest() {
        endToEndPage.endToEndScenario(driver);
    }
}