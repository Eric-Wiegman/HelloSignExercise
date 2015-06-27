package com.hellosign.test.testng;

import com.hellosign.test.pages.LandingPage;
import com.hellosign.test.pages.LoginPage;
import com.hellosign.test.webtestsbase.WebDriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The Test Class to verify elements of the Login page.
 */
public class LoginTest {

    /**
     * Before test routine -- launches the browser.
     */
    @BeforeTest
    public void beforeTest() {
        WebDriverFactory.startBrowser();
    }

    /**
     * Test login.
     * <br><br>
     * TEST 1:<br>
     * Go to www.hellosign.com<br>
     * click on "LOG IN" in the upper right<br>
     * enter "notanemail" in the email address field<br>
     * enter "notapassword" in the password field<br>
     * click "SIGN IN".<br>
     * You should verify that the following error is displayed: "Invalid email address"<br>
     * <br><br>
     * TEST 2:<br>
     * Go to www.hellosign.com<br>
     * click on "LOG IN" in the upper right<br>
     * enter "email@example.com" in the email address field<br>
     * enter "wrongpass" in the password field<br>
     * click "SIGN IN".<br>
     * You should verify that the following error is displayed: "Invalid username/password combo."<br>
     * <br>
     *
     * @param identifier         the identifier for the specified data-driven scenario
     * @param email              the email used to log in
     * @param password           the password used to log in
     * @param expectedErrMessage the expected error message
     */
    @Test(dataProvider = "loginDataProvider", dataProviderClass = LoginDataProvider.class)
    public void testLogin(
            String identifier,
            String email,
            String password,
            String expectedErrMessage) {

        LandingPage landingPage = new LandingPage();
        landingPage.signIn();

        LoginPage loginPage = new LoginPage();
        if (loginPage.isPageOpened()) {

            String actualErrMessage = loginPage.login(email, password);

            Assert.assertEquals(
                    actualErrMessage,
                    expectedErrMessage,
                    "The error messages do not match -- ");
        }
    }

    /**
     * After test, where we quit the browser
     */
    @AfterTest
    public void afterTest() {
        WebDriverFactory.finishBrowser();
    }
}
