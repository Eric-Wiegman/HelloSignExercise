TABLE OF CONTENTS
-----------------
<ol>
<li> Introduction  </li>
<li> Statement of Work </li>
<li> Requirements </li>
<li> What was automated (Scenario 1) </li>
<li> What was automated (Scenario 2) </li>
<li> Project (Location, Structure) </li>
<li> API Documentation (Javadoc) </li>
<li> Command-line Invocation </li>
<li> Output (Expected) </li>
</ol>


1. Introduction
---------------------------------------------------------------------------
This is the README.md file for the 'HelloSign Exercise' presented to Eric
    Wiegman as part of his consideration for the _"Lead QA Engineer"_
    position at HelloSign.

2. Statement of Work
------------------------------
 I have created a web UI test project using the Selenium WebDriver open-source
    technology and have used Maven as the tool to perform the build and deploy
    the test (running the TestNG suite with the surefire plugin). The client
    language is Java and the test harness used is TestNG.

3. Specified Project Requirements
-------------------------------------------------------------
1. Keep in mind that code should be flexible, expandable and
    maintainable.
1. You should be able to run tests against the Firefox and Chrome
    browsers.
1. Add the necessary instructions to the README.md file in English on how to
    run the code from the command line.


3.1. Notes on Requirements
--------------------------
1. Object Oriented Principles and Design considerations were applied when
    designing the java classes.
2. The /src/main/resources/resources.properties file has an entry for
    specifying which browser (Firefox or Chrome) to run against.
3. The tool used is Selenium WebDriver (2.45.0).
4. Maven is used to manage project dependencies. The tool used is Selenium
    (with the Java 8 client) and the harness used is TestNG (6.8.17).
5. The PageFactory pattern is used.
6. This file contains instructions on how to run the test from command line.


4. The following is a general outline of what was automated for TEST Scenario 1:
---------------------------------------------------------------------------
1. Go to www.hellosign.com
1. click on `"LOG IN"` in the upper right
1. enter `"notanemail"` in the email address field 
1. enter `"notapassword"` in the password field
1. click `"SIGN IN"`
1. verify that the following error is displayed:
    _"Invalid email address"_

5. The following is a general outline of what was automated for TEST Scenario 2:
---------------------------------------------------------------------------
1. Go to www.hellosign.com
1. click on `"LOG IN"` in the upper right
1. enter `"email@example.com"` in the email address field 
1. enter `"wrongpass"` in the password field
1. click `"SIGN IN"`
1. verify that the following error is displayed:
    _"Invalid username/password combo."_

Unless otherwise noted, the single test case run was parameterized using the
    TestNG DataProvider -- with that data separated from the code in its own
    /src/test/java/com/hellosign/test/testng/LoginDataProvider file.

6. The project is stored on GitHub at public repository HelloSignExercise.
---------------------------------------------------------------------------
6.1 The directory structure is shown below:

<pre>
│   pom.xml
│   README.md
│
└───src
    ├───docs
    │   │   allclasses-frame.html
    │   │   allclasses-noframe.html
    │   │   constant-values.html
    │   │   deprecated-list.html
    │   │   help-doc.html
    │   │   index.html
    │   │   overview-frame.html
    │   │   overview-summary.html
    │   │   overview-tree.html
    │   │   package-list
    │   │   script.js
    │   │   stylesheet.css
    │   │
    │   ├───com
    │   │   └───hellosign
    │   │       └───test
    │   │           ├───pages
    │   │           │       LandingPage.html
    │   │           │       LoginPage.html
    │   │           │       package-frame.html
    │   │           │       package-summary.html
    │   │           │       package-tree.html
    │   │           │
    │   │           ├───testng
    │   │           │       LoginDataProvider.html
    │   │           │       LoginTest.html
    │   │           │       package-frame.html
    │   │           │       package-summary.html
    │   │           │       package-tree.html
    │   │           │
    │   │           ├───utils
    │   │           │       Consts.html
    │   │           │       package-frame.html
    │   │           │       package-summary.html
    │   │           │       package-tree.html
    │   │           │       Utils.html
    │   │           │
    │   │           └───webtestsbase
    │   │                   BasePage.html
    │   │                   Browser.html
    │   │                   package-frame.html
    │   │                   package-summary.html
    │   │                   package-tree.html
    │   │                   WebDriverFactory.html
    │   │
    │   └───index-files
    │           index-1.html
    │           index-10.html
    │           index-11.html
    │           index-12.html
    │           index-13.html
    │           index-14.html
    │           index-15.html
    │           index-16.html
    │           index-17.html
    │           index-18.html
    │           index-19.html
    │           index-2.html
    │           index-20.html
    │           index-3.html
    │           index-4.html
    │           index-5.html
    │           index-6.html
    │           index-7.html
    │           index-8.html
    │           index-9.html
    │
    ├───main
    │   ├───java
    │   │   └───com
    │   │       └───hellosign
    │   │           └───test
    │   │               ├───utils
    │   │               │       Consts.java
    │   │               │       Utils.java
    │   │               │
    │   │               └───webtestsbase
    │   │                       BasePage.java
    │   │                       Browser.java
    │   │                       WebDriverFactory.java
    │   │
    │   └───resources
    │           resources.properties
    │           testng.xml
    │
    └───test
        └───java
            └───com
                └───hellosign
                    └───test
                        ├───pages
                        │       LandingPage.java
                        │       LoginPage.java
                        │
                        └───testng
                                LoginDataProvider.java
                                LoginTest.java
</pre>

6.2 Please note that the directory structure is important, and file/directory
    changes should **not** be made. Failure to leave the structure as is will
    cause Maven, the surefire plugin, or other tools to not recognize the
    classpath items correctly, leading to failures.

7. API Documentation (Javadoc)
------------------------
For more information on the defined java elements in this project, invoke
    index.html at src/docs and use the API viewer to read the Javadoc
    supplied text.

8. Command-line invocation
---------------------------
1. To call the test from the command line (from within your Windows (DOS)
    Command Prompt or Macintosh/Linux Terminal), you need to ensure some
    prerequisites have first been met.
2. You should have your path set so that it will recognize the Maven binary
    'mvn' no matter which directory you are currently browsing.
3. As it is required by Maven, if you have not already done so set your
    JAVA\_HOME environment variable to be where you installed your Java
    (for instance, 'C:\Program Files\Java\jdk1.8.0_40' if using defaults for
    Windows installation).
4. To simplify the command line call, you should use your Terminal to
    navigate to the directory where the desired pom.xml file is located.
    For this case, this is the directory 'HelloSign'. In that way, the command
    line call will assume there is only one POM file to be run and will
    assume it is in the current directory.
5. Additionally, it appears maven cannot handle dependencies if they are
    executables, so if using Selenium WebDriver to automate the product on a
    Chrome browser you need to retrieve the latest chromedriver executable
    and unzip it to your local drive (see the resources.properties
    'chromedriverExeLocation' item to see how to specify the relative path to
     this required executable ... for my Win32 setup, this was
     /src/main/resources/chromedriver_win32/chromedriver.exe, for me).

     You may find this zipped chromedriver executable at
     [https://sites.google.com/a/chromium.org/chromedriver/downloads](https://sites.google.com/a/chromium.org/chromedriver/downloads)

8.2. The following is the command line text to be entered in the Command Prompt
    or Terminal:

        mvn compile test

9. Output
---------
9.1. The expected (successful) command line output is shown below
<pre>
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building login-test 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-resources-plugin:2.5:resources (default-resources) @ login-test
 ---
[debug] execute contextualize
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources,
i.e. build is platform dependent!
[INFO] Copying 3 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ login-test ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:2.5:resources (default-resources) @ login-test
 ---
[debug] execute contextualize
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources,
i.e. build is platform dependent!
[INFO] Copying 3 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ login-test ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-resources-plugin:2.5:testResources (default-testResources) @ lo
gin-test ---
[debug] execute contextualize
[WARNING] Using platform encoding (Cp1252 actually) to copy filtered resources,
i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory C:\Users\Eric the Boss\Documents\GitH
ub\HelloSignExercise\src\test\resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ login-t
est ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- maven-surefire-plugin:2.14.1:test (default-test) @ login-test ---
[INFO] Surefire report directory: C:\Users\Eric the Boss\Documents\GitHub\HelloS
ignExercise\target\surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running TestSuite
Starting ChromeDriver 2.15.322448 (52179c1b310fec1797c81ea9a20326839860b7d3) on
port 30958
Only local connections are allowed.
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 30.247 sec

Results :

Tests run: 2, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 37.832s
[INFO] Finished at: Sat Jun 27 09:13:31 PDT 2015
[INFO] Final Memory: 8M/126M
[INFO] ------------------------------------------------------------------------
</pre>

9.2. At the point where the 'Running TestSuite' is announced, Maven calls
    surefire, which in turn calls TestNG harness that runs the XML suite in
    Selenium Java code. This performs the steps outlined in README sections 4
    and 5.

9.3. The directory 'target' is created in the 'HelloSign' root, where binaries
    and pass/fail report files are found.

9.4. To see a graphical representation of the pass/fail/skip data associated
    with the testNG Suite run, open the file /target/surefire-reports/index.html
    in your favorite browser.
