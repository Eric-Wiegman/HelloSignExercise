package com.hellosign.test.webtestsbase;

/**
 * Created by Sidelnikov Mikhail on 17.09.14.
 * This class represents browsers. For add support of your browser - add it to this enum
 *  <br>
 * Modified by Eric Wiegman on 26.06.15.
 * Minor changes in javadoc only
 */
public enum Browser {
    /**
     * Enumerates the FIREFOX browser.
     */
    FIREFOX("firefox"),
    /**
     * Enumerates the CHROME browser.
     */
    CHROME("chrome"),
    /**
     * Enumerates the IE10 (Internet Explorer 10) browser.
     */
    IE10("ie10"),
    /**
     * Enumerates the SAFARI browser.
     */
    SAFARI("safari");
    /**
     * The Browser name.
     */
    private final String browserName;

    /**
     * Instantiates a new Browser.
     *
     * @param browserName the browser name
     */
    Browser(String browserName) {
        this.browserName = browserName;
    }

    /**
     * returns browser object by name
     *
     * @param name name of browser
     * @return browser object
     */
    public static Browser getByName(String name) {
        for (Browser browser : values()) {
            if (browser.getBrowserName().equalsIgnoreCase(name)) {
                return browser;
            }
        }
        return null;
    }

    /**
     * Gets browser name.
     *
     * @return the browser name
     */
    private String getBrowserName() {
        return browserName;
    }


}
