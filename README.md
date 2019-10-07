# Automated test example in Java with Cucumber and Selenium WebDriver #

This project is an example of UI automated functional test for a borrowing calculator using Selenium and Cucumber.

Test scenarios are described in the feature file located here .cucumber-test-sample/features/Calculator.feature.


## Installation ##

You need to have [Java 8 JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) installed along with [maven](https://maven.apache.org/download.cgi).

This test runs in Chrome browser. 

Change location of 'chromedriver' as per your file system in below file:

cucumber-test-sample/src/test/java/stepDefinition/TestScenario.java

*This line : System.setProperty("webdriver.chrome.driver", "/Users/anupam/Desktop/Automation Framework/chromedriver");*


To run the tests locally with Firefox, install GeckoDriver from [here](https://github.com/mozilla/geckodriver/releases) and add its location to your system PATH.

To install all dependencies, run 

```console
$ mvn clean install
```

## Running tests ##

```console
$ mvn test
```

After tests are run, reports are generated at : /target/cucumber-reports/index.html
