package com.hellosign.test.testng;

import org.testng.annotations.DataProvider;

/**
 * The Class LoginDataProvider, separating data from code.
 */
public class LoginDataProvider {

    /**
     * DataProvider for the HelloSign Interview Tests.
     *
     * @return the object[][], which represents the data to be used in the Test.
     */
    @DataProvider
    public static Object[][] helloSignLoginDataProvider() {
        return new Object[][]{
                /*
                String ID, String email, String password,
                      String expectedErrMessage)
                */
                {"TEST 1", "notanemail", "notapassword",
                        "Invalid email address"},
                {"TEST 2", "email@example.com", "wrongpass",
                        "Invalid username/password combo."}

        };

    }

}
