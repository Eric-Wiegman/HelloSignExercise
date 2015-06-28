package com.hellosign.test.webtestsbase;

import com.hellosign.test.utils.Consts;
import com.hellosign.test.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sidelnikov Mikhail on 18.09.14.
 * Base class for web tests. It contains web driver {@link WebDriver} instance,
 * used in all tests. All communications with driver should be done through
 * this class
 * <br>
 * Modified by Eric Wiegman on 26.06.15.
 * Major changes in startBrowser() method
 */
public class WebDriverFactory {

    /**
     * The constant IMPLICIT_WAIT_TIMEOUT.
     */
    private static final long IMPLICIT_WAIT_TIMEOUT = 5;
    /**
     * The constant utilities, which is an instantiation of the generic Utility
     * class.
     */
    private static Utils utilities = new Utils();
    /**
     * The constant driver.
     */
    private static volatile WebDriver driver;

    /**
     * Getting of pre-configured {@link WebDriver} instance.
     * Please use this method only after calling {@link #startBrowser()
     * startBrowser}** method
     *
     * @return webdriver object, or throw IllegalStateException,
     * if driver has not been initialized
     */
    public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        } else {
            throw new IllegalStateException("Driver has not been initialized. "
                    + "Please call WebDriverFactory.startBrowser() before using"
                    + " this method");
        }
    }

    /**
     * Finishes browser.
     */
    public static void finishBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Starts the browser for the test.
     */
    public static void startBrowser() {


        String sBrowser = utilities.getBrowserValueFromPropertyFile();


        Browser browser = null;
        try {
            browser = Browser.valueOf(sBrowser.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The browser:" + Consts.SPACE
                    + Consts.QUOTE + sBrowser + Consts.QUOTE + Consts.SPACE
                    + "specified in the property file" + Consts.SPACE
                    + Consts.QUOTE + Consts.PROPERTY_FILE + Consts.QUOTE
                    + Consts.SPACE + "is invalid." + Consts.SPACE
                    + "Please check the entry and try again!");
        }

        if (driver == null) {
            switch (browser) {
                case FIREFOX:
                    driver = new FirefoxDriver();
                    break;
                case CHROME:
                    utilities.setChromeDriverProperty();
                    driver = new ChromeDriver();
                    break;
                case IE10:
                    driver = new InternetExplorerDriver();
                    break;
                case SAFARI:
                    driver = new SafariDriver();
                    break;
                default:
                    throw new IllegalStateException("Unsupported browser type");

            }
            driver.manage().timeouts().implicitlyWait(
                    IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        } else {
            throw new IllegalStateException("Driver's already been initialized."
                    + Consts.SPACE + "Quit it before using this method");
        }
    }
}

