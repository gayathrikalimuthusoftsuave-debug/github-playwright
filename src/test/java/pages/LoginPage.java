package pages;

import com.microsoft.playwright.Page;
import utils.ConfigReader;

public class LoginPage {

    private Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    private String username = "#username";
    private String password = "#password";
    private String loginBtn = "button[type='submit']";

    public void navigate() {
        page.navigate(ConfigReader.get("baseURL"));
    }

    public void login(String user, String pass) {
        page.fill(username, user);
        page.fill(password, pass);
        page.click(loginBtn);
    }

    // ✅ Add validation methods (VERY IMPORTANT)

    public boolean isLoginSuccessful() {
        return page.url().contains("secure");
    }

    public boolean isLoginFailed() {
        page.waitForSelector("text=Your username is invalid!");
        return page.locator("text=Your username is invalid!").isVisible();
    }
}