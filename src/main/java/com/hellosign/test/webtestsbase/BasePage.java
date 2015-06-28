package com.hellosign.test.webtestsbase;

import com.hellosign.test.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Sidelnikov Mikhail on 19.09.14.
 * This is the main class for pages. When you create your page -
 * you must extend your class from this
 * <br>
 * Modified by Eric Wiegman on 26.06.15.
 * Minor changes in package reference only
 */
public abstract class BasePage {
    /**
     * The constant WAIT_FOR_PAGE_LOAD_IN_SECONDS.
     */
    private static final int WAIT_FOR_PAGE_LOAD_IN_SECONDS = 5;

    /**
     * Instantiates a new Base page.
     *
     * @param openPageByUrl the open page by url
     */
    protected BasePage(final boolean openPageByUrl) {
        if (openPageByUrl) {
            openPage();
        }
        PageFactory.initElements(getDriver(), this);
        waitForOpen();
    }

    /**
     * In subclasses  should be used for page opening.
     */
    protected abstract void openPage();

    /**
     * checks is page opened.
     *
     * @return true if opened
     */
    protected abstract boolean isPageOpened();

    /**
     * Waiting for page opening.
     */
    private void waitForOpen() {
        int secondsCount = 0;
        boolean isPageOpenedIndicator = isPageOpened();
        while (!isPageOpenedIndicator
                && WAIT_FOR_PAGE_LOAD_IN_SECONDS > secondsCount) {
            Utilities.waitForSeconds(1);
            secondsCount++;
            isPageOpenedIndicator = isPageOpened();
        }
        if (!isPageOpenedIndicator) {
            throw new AssertionError("Page was not opened");
        }
    }

    /**
     * getting webdriver instance.
     *
     * @return initialized in tests webdriver instance.
     */
    protected static WebDriver getDriver() {
        return com.hellosign.test.webtestsbase.WebDriverFactory.getDriver();
    }

}
