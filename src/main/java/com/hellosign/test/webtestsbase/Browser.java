package com.hellosign.test.webtestsbase;

/**
 * Created by Sidelnikov Mikhail on 17.09.14.
 * This class represents browsers. For add support of your browser -
 * add it to this enum
 * <br>
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
     * @param browserNameParam the browser name
     */
    Browser(final String browserNameParam) {
        this.browserName = browserNameParam;
    }

    /**
     * returns browser object by name.
     *
     * @param name name of browser
     * @return browser object
     */
    public static Browser getByName(final String name) {

        Browser browserReturn = null;

        for (Browser browser : values()) {
            if (browser.getBrowserName().equalsIgnoreCase(name)) {
                browserReturn = browser;
            }
        }
        return browserReturn;
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
