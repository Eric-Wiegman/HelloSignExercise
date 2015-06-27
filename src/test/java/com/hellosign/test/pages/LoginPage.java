package com.hellosign.test.pages;

import com.hellosign.test.utils.Consts;
import com.hellosign.test.webtestsbase.BasePage;
import com.hellosign.test.webtestsbase.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

/**
 * The class representing the HelloSign landing page, where one can log in --
 * using the pop-up iFrame.
 */
public class LoginPage extends BasePage {

    /**
     * The constant INLINE_ERR_CLASSNAME.
     */
    private static final String INLINE_ERR_CLASSNAME = "m-modal--error";

    /**
     * The Button that allows you to sign in with Google credentials.
     */
    @FindBy(className = "m-login--modal-google-login")
    private WebElement btnSignInWithGoogle;

    /**
     * The login page's email textField.
     */
    @FindBy(id = "login_email_address_input")
    private WebElement tfLoginEmail;

    /**
     * The login page's password textField..
     */
    @FindBy(id = "login_password_input")
    private WebElement tfLoginPassword;

    /**
     * The Link you click if you forgot your password.
     */
    @FindBy(className = "m-login--forgot-pwd-link")
    private WebElement linkForgotPassword;

    /**
     * The Button to log you in.
     */
    @FindBy(className = "m-login--modal-google-login-button")
    private WebElement btnLogin;

    /**
     * The Inline error text.
     */
    @FindBy(className = INLINE_ERR_CLASSNAME)
    private WebElement inlineErrorText;

    /**
     * Instantiates a new HelloSign login page.
     */
    public LoginPage() {
        super(false);
    }

    /**
     * Open page.
     */
    @Override
    protected void openPage() {
        //do nothing. Clicking button on landing page takes us here
    }

    /**
     * Is page opened.
     *
     * @return the boolean
     */
    @Override
    public boolean isPageOpened() {
        try {
            return btnLogin.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Login method.
     *
     * @param email the username (email, actually) used when logging in
     * @param password the password used when logging in
     * @return the string
     */
    public String login(String email, String password) {

        String errMsg = "No error message reported";

        WebDriver wd = WebDriverFactory.getDriver();

        tfLoginEmail.sendKeys(email);
        tfLoginPassword.sendKeys(password);
        btnLogin.click();

        WebDriverWait wait = new WebDriverWait(wd, Consts.QUARTER_MINUTE);
        WebElement wErrorMsgShows = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className
                        (INLINE_ERR_CLASSNAME)));

        if (wErrorMsgShows != null) {
            errMsg = wErrorMsgShows.getText();
        }

        return errMsg;
    }

}

