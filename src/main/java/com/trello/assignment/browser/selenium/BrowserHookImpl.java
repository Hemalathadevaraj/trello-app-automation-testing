package com.trello.assignment.browser.selenium;

import com.trello.assignment.browser.BrowserHook;
import com.trello.assignment.browser.selenium.factory.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * This class provides implementation for {BrowserHook} acts as a layer between Pages and Browser
 */
public class BrowserHookImpl implements BrowserHook {

    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;
    private final PageElementsUtils pageElementsUtils;

    public BrowserHookImpl(String browserName) {
        webDriver = DriverFactory.getDriver(browserName);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        pageElementsUtils = new PageElementsUtils();
    }

    public void openUrl(String url) {
        webDriver.get(url);
    }

    public void findElementAndClick(String elementId) {
        webDriver.findElement(pageElementsUtils.findElementById(elementId)).click();
    }

    public void findElementAndClick(String elementId, boolean checkIsVisible, boolean checkIsClickable) {
        if (checkIsVisible) {
            waitUntilElementDisplayed(elementId);
        }
        if (checkIsClickable) {
            waitUntilElementClickable(elementId);
        }
        webDriver.findElement(pageElementsUtils.findElementById(elementId)).click();
    }

    public void findElementAndFillText(String textField, String textValue) {
        webDriver.findElement(pageElementsUtils.findElementById(textField)).sendKeys(textValue);
    }

    public String findElementAndGetText(String elementId) {
        return webDriver.findElement(pageElementsUtils.findElementById(elementId)).getText();
    }

    private void waitUntilElementClickable(String elementId) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(pageElementsUtils.findElementById(elementId)));
    }

    private void waitUntilElementDisplayed(String elementId) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(pageElementsUtils.findElementById(elementId)));
    }

    public byte[] takeScreenShot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    public void closeDriver() {
        webDriver.close();
    }

    public boolean findElementAndCheckIsEnabled(String elementId) {
        return webDriver.findElement(pageElementsUtils.findElementById(elementId)).isEnabled();
    }
}
