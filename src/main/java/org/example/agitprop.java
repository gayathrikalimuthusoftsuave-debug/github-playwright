package org.example;

import com.microsoft.playwright.*;

public class agitprop {
    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            // Launch browser
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            );

            // Open page
            Page page = browser.newPage();

            // Navigate
            page.navigate("https://practicetestautomation.com/practice-test-login/");

            // Perform login directly
            page.fill("#username", "student");
            page.fill("#password", "Password123");
            page.locator("button:has-text('Submit')").click();

            // Validate
            page.waitForSelector("text=Logged In Successfully");

            System.out.println("Login Successful ✅");

            page.waitForTimeout(5000);
            browser.close();
        }
    }
}