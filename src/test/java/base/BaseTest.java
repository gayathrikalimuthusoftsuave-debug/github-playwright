package base;

import com.microsoft.playwright.*;
import utils.ConfigReader;

public class BaseTest {

    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static Page page;


    public static void init() {
        playwright = Playwright.create();


        String browserType = ConfigReader.get("browser");

        if (browserType.equalsIgnoreCase("chromium")) {
            browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
        } else if (browserType.equalsIgnoreCase("firefox")) {
            browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
        } else {
            browser = playwright.webkit().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );
        }

        context = browser.newContext();
        page = context.newPage();

        page.setDefaultTimeout(
                Integer.parseInt(ConfigReader.get("timeout"))
        );// 👈 wait handling
    }

    public static void tearDown() {
        try {
            if (browser != null) {
                browser.close();
            }
            if (playwright != null) {
                playwright.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}