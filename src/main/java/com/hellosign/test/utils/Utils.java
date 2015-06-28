package com.hellosign.test.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The class Utils -- general utilities.
 */
public class Utils {

    /**
     * waiting for seconds.
     *
     * @param timeoutInSeconds timeout in seconds for wait
     */
    public static void waitForSeconds(final int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * Consts.ONE_THOUSAND_LONG);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the Operating System name.
     *
     * @return the OS Name
     */
    private static String getOS() {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            os = "windows";
        } else if (os.contains("mac")) {
            os = "mac";
        } else if (os.contains("nix")) {
            os = "nix";
        } else if (os.contains("solaris")) {
            os = "solaris";
        } else {
            throw new AssertionError("Your OS" + Consts.SPACE + Consts.QUOTE
                    + os + Consts.QUOTE + Consts.SPACE + "is not supported");
        }

        return os;
    }

    /**
     * Gets browser value from property file.
     *
     * @return the browser value from property file
     */
    public final String getBrowserValueFromPropertyFile() {

        String defaultBrowser = "firefox";
        String browserValue = defaultBrowser;

        try {
            browserValue = getStringFromPropFile(
                    Consts.PROPERTY_FILE, "browser");
        } catch (IOException e) {
            System.out.println("Cannot find property file" + Consts.SPACE
                    + Consts.QUOTE + Consts.PROPERTY_FILE + Consts.QUOTE
                    + ", so will try using the default browser" + Consts.SPACE
                    + defaultBrowser + "." + Consts.NEWLINE);
        }
        return browserValue;
    }

    /**
     * Gets chrome driver executable path from property file.
     *
     * @return the chrome driver executable path from property file
     */
    private String getChromeDriverExePathFromPropertyFile() {

        String chromedriverExeLocation = "unknown path";

        try {
            chromedriverExeLocation = getStringFromPropFile(
                    Consts.PROPERTY_FILE, "chromedriverExeLocation");
        } catch (IOException e) {
            /*
            we cannot find property file so if specified to use Chrome,
            we can't use it ... as we don't know where to find the
            chromeDriver executable. Just fall back on FireFox default, if
            warranted.
            */
            if ("chrome".equals(getBrowserValueFromPropertyFile()
                    .toLowerCase())) {
                throw new AssertionError("Can't find the chromedriver "
                        + "Executable -- suggest changing the 'browser' config "
                        + "value to 'firefox'");
            }
        }
        return chromedriverExeLocation;
    }

    /**
     * Gets base URL value from property file.
     *
     * @return the base URL value from property file
     */
    public final String getBaseUrlValueFromPropertyFile() {

        String baseUrl = "127.0.0.1";

        try {
            baseUrl = getStringFromPropFile(Consts.PROPERTY_FILE, "baseUrl");
        } catch (IOException e) {
            throw new AssertionError(Consts.NEWLINE + Consts.TAB
                    + "Cannot find property file" + Consts.SPACE + Consts.QUOTE
                    + Consts.PROPERTY_FILE + Consts.QUOTE + Consts.SPACE
                    + "and thus cannot specify which URL to navigate "
                    + "to in th +e browser, so test will fail." + Consts.NEWLINE
                    + Consts.TAB + "Additionally, make sure the property file"
                    + " can be found and the" + Consts.SPACE + Consts.QUOTE
                    + "baseUrl" + Consts.QUOTE + Consts.SPACE + "entry has the "
                    + "correct value." + Consts.NEWLINE
            );

        }
        return baseUrl;
    }

    /**
     * Gets string value from the property file.
     *
     * @param propFileName the property file name
     * @param propString   the property string
     * @return the value from property file
     * @throws IOException an I/O exception
     */
    private String getStringFromPropFile(
            final String propFileName,
            final String propString
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

    /**
     * Sets chromeDriver property.
     */
    public final void setChromeDriverProperty() {

        String driverFolder;
        String driverExe = "chromedriver";

        String chromedriverExeLocation =
                getChromeDriverExePathFromPropertyFile();

        switch (Utils.getOS()) {
            case "windows":
                driverFolder = "chromedriver_win32";
                driverExe = driverExe + ".exe";
                break;
            case "mac":
                driverFolder = "chromedriver_mac32";
                break;
            case "nix":
                //note: only supporting 32-bit Linux for now
                driverFolder = "chromedriver_linux32";
                break;
            case "solaris":
                throw new AssertionError("Solaris is not supported for "
                        + "ChromeDriver Executable");
            default:
                throw new AssertionError("Your OS is not supported for "
                        + "ChromeDriver Executable");
        }

        System.setProperty(
                "webdriver.chrome.driver",
                System.getProperty("user.dir")
                        + getFilePathSeparator()
                        + chromedriverExeLocation
                        + getFilePathSeparator()
                        + driverFolder
                        + getFilePathSeparator()
                        + driverExe);
    }

    /**
     * Gets file path separator.
     *
     * @return the file path separator
     */
    private String getFilePathSeparator() {
        String separator;

        if (getOS().equals("windows")) {
            separator = Consts.BACKSLASH;
        } else {
            separator = Consts.SLASH;
        }

        return separator;
    }
}
