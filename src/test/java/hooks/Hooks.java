package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks {

    @After
    public void takeScreenshot(Scenario scenario) {

        if (scenario.isFailed()) {

            try {
                if (BaseTest.page != null) {

                    byte[] screenshot = BaseTest.page.screenshot();

                    scenario.attach(
                            screenshot,
                            "image/png",
                            scenario.getName() + " - Failed"
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}