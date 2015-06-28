package com.hellosign.test.pages;

import com.hellosign.test.utils.Utilities;
import com.hellosign.test.webtestsbase.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.NoSuchElementException;

/**
 * The Class representing the Landing page.
 */
public class LandingPage extends BasePage {

    /**
     * The constant PAGE_URL.
     */
    private static final String PAGE_URL =
            new Utilities().getBaseUrlValueFromPropertyFile();

    /**
     * The Login link.
     */
    @FindBy(className = "m-link-list--log-in")
    private WebElement loginLink;

    /**
     * Instantiates a new HelloSign Landing page.
     */
    public LandingPage() {
        super(true);
    }

    /**
     * Open page.
     */
    @Override
    protected final void openPage() {
        getDriver().get(PAGE_URL);
    }

    /**
     * Is page opened.
     *
     * @return the boolean result
     */
    @Override
    public final boolean isPageOpened() {

        boolean bReturn;

        try {
            bReturn =  loginLink.isDisplayed();
        } catch (NoSuchElementException e) {
            bReturn = false;
        }

        return bReturn;
    }

    /**
     * Sign in.
     */
    public final void signIn() {
        loginLink.click();
    }

}

