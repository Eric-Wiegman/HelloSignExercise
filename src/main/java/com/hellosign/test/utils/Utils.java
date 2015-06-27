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
     *
     * @param timeoutInSeconds timeout in seconds for wait
     */
    public static void waitForSeconds(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getOS() {

        String os = System.getProperty("os.name").toLowerCase();

        if (isWindows()) {
            os = "windows";
        } else if (isMac()) {
            os = "mac";
        } else if (isUnix()) {
            os = "nix";
        } else if (isSolaris()) {
            os = "solaris";
        } else {
            throw new AssertionError("Your OS is not supported");
        }

        return os;
    }

    private static boolean isWindows() {
        return (getOS().contains("win"));
    }

    private static boolean isMac() {
        return (getOS().contains("mac"));
    }

    private static boolean isUnix() {
        return (getOS().contains("nix") || getOS().contains("nux") || getOS
                ().contains("aix"));
    }

    private static boolean isSolaris() {
        return (getOS().contains("sunos"));
    }

    /**
     * Gets browser value from property file.
     *
     * @return the browser value from property file
     */
    public String getBrowserValueFromPropertyFile() {

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
     * @param propString   the property string
     * @return the value from property file
     * @throws IOException an I/O exception
     */
    private String getStringFromPropFile(
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

    public void setChromeDriverProperty() {

        String driverFolder;
        String driverExe = "chromedriver";

        String chromedriverExeLocation = getChromeDriverExePathFromPropertyFile();

        switch (Utils.getOS()) {
            case "windows":
                driverFolder = "chromedriver_win32";
                driverExe = driverExe + ".exe";
                break;
            case "mac":
                driverFolder = "chromedriver_mac32";
                break;
            case "nix":
                throw new AssertionError("Supported by Google, but not yet implemented " +
                        "in Automation Framework");
            case "solaris":
                throw new AssertionError("Solaris is not supported for " +
                        "ChromeDriver Executable");
            default:
                throw new AssertionError("Your OS is not supported for " +
                        "ChromeDriver Executable");
        }

        System.setProperty(
                "webdriver.chrome.driver",
                System.getProperty("user.dir") +
                        getFilePathSeparator() +
                        chromedriverExeLocation +
                        getFilePathSeparator() +
                        driverFolder +
                        driverExe);
    }

    private String getFilePathSeparator() {
        String separator = Consts.SLASH;

        if (isWindows()) {
            separator = Consts.BACKSLASH;
        } else {
            //all else uses the (already initialized) default forward slash
        }

        return separator;
    }
}
