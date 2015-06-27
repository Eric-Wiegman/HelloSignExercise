package com.hellosign.test.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The class Utils -- general utilities
 */
public class Utils {

    /**
     * waiting for seconds
     * @param timeoutInSeconds timeout in seconds for wait
     */
    public static void waitForSeconds(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets browser value from property file.
     *
     * @return the browser value from property file
     */
    public String getBrowserValueFromPropertyFile () {

        String defaultBrowser = "firefox";
        String browserValue = defaultBrowser;

        try {
            browserValue = getStringFromPropFile(Consts.PROPERTY_FILE, "browser");
        } catch (IOException e) {
            System.out.println("Cannot find property file " + Consts
                    .PROPERTY_FILE + ", so using default " + defaultBrowser
                    + ".");
        }
        return browserValue;
    }

    /**
     * Gets chrome driver executable path from property file.
     *
     * @return the chrome driver executable path from property file
     */
    public String getChromeDriverExePathFromPropertyFile () {

        String chromedriverExeLocation = "unknown path";

        try {
            chromedriverExeLocation  = getStringFromPropFile(
                    Consts.PROPERTY_FILE, "chromedriverExeLocation");
        } catch (IOException e) {
            /*
            we cannot find property file so if specified to use Chrome,
            we can't use it ... as we don't know where to find the
            chromeDriver executable. Just fall back on FireFox default, if
            warranted.
            */
            if ("chrome".equals( getBrowserValueFromPropertyFile ()
                    .toLowerCase())) {
                throw new AssertionError("Can't find the chromedriver " +
                        "Executable -- suggest changing the 'browser' config " +
                        "value to 'firefox'");
            }
        }
        return chromedriverExeLocation;
    }

    /**
     * Gets base URL value from property file.
     *
     * @return the base URL value from property file
     */
    public String getBaseUrlValueFromPropertyFile() {

        String baseUrl = "127.0.0.1";

        try {
            baseUrl = getStringFromPropFile(Consts.PROPERTY_FILE, "baseUrl");
        } catch (IOException e) {
            throw new AssertionError("Cannot find property file, so test will fail.");
        }
        return baseUrl;
    }

    /**
     * Gets string value from the property file.
     *
     * @param propFileName the property file name
     * @param propString the property string
     * @return the value from property file
     * @throws IOException an I/O exception
     */
    private String getStringFromPropFile (
            String propFileName,
            String propString
    )
            throws IOException {

        String propStringVal = "Error";

        Properties props = getPropertiesFromClasspath(propFileName);
        propStringVal = props.getProperty(propString);

        return propStringVal;
    }

    /**
     * Gets properties from classpath.
     *
     * @param propFileName the property file name
     * @return the properties from classpath
     * @throws IOException the I/O exception
     */
    private Properties getPropertiesFromClasspath(
            String propFileName
    ) throws IOException {
        Properties props = new Properties();
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(propFileName);

        if (inputStream == null) {
            throw new FileNotFoundException("property file '" + propFileName
                    + "' not found in the classpath");
        }

        props.load(inputStream);

        return props;
    }

}
