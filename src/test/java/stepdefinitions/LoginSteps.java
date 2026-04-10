package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import pages.LoginPage;
import org.junit.Assert;

public class LoginSteps {

    LoginPage loginPage;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        BaseTest.init();
        loginPage = new LoginPage(BaseTest.page);
        loginPage.navigate();
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("login should be {string}")
    public void login_should_be(String expectedResult) {

        if (expectedResult.equals("success")) {
            Assert.assertTrue("Login should be successful",
                    loginPage.isLoginSuccessful());
        } else {
            Assert.assertTrue("Login should fail",
                    loginPage.isLoginFailed());
        }

        BaseTest.tearDown();
    }

}