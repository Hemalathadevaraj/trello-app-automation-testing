package com.trello.assignment.browser;

/**
 * Browser Hook provides interactive methods between Page actions and Browsers {BrowserHookImpl}
 */
public interface BrowserHook {

    void openUrl(String url);

    void findElementAndClick(String elementId);

    void findElementAndClick(String elementId, boolean checkIsVisible, boolean checkIsClickable);

    void findElementAndFillText(String elementId, String textValue);

    String findElementAndGetText(String elementId);

    byte[] takeScreenShot();

    void closeDriver();

    boolean findElementAndCheckIsEnabled(String elementId);
}
