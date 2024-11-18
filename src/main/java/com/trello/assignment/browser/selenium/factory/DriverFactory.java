package com.trello.assignment.browser.selenium.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * DriverFactory to create WebDriver based on chosen browser
 */
public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        if (browser == null || browser.isEmpty()) {
            throw new IllegalArgumentException("Browser name is required");
        }
        WebDriver webDriver = getWebDriver(browser);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        return webDriver;
    }

    private static WebDriver getWebDriver(String browser) {
        WebDriver webDriver;
        switch (browser.toLowerCase()) {
            case "chrome":
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                // private mode or incognito option can be used if necessary
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-private");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                webDriver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("unsupported browser" + browser);
        }
        return webDriver;
    }
}