package com.trello.assignment.pages;

import com.trello.assignment.browser.BrowserHook;
import com.trello.assignment.browser.selenium.Constants;
import com.trello.assignment.utils.ConfigProperties;

public class LoginPage {
    private final BrowserHook browserHook;

    public LoginPage(BrowserHook browserHook) {
        this.browserHook = browserHook;
    }

    public void openUrl() {
        browserHook.openUrl(ConfigProperties.getPropertyValue("trelloUrl"));
    }

    public void clickLoginButton() {
        browserHook.findElementAndClick(Constants.LOGIN_PAGE_LOGIN_BUTTON);
    }

    public void enterEmailID() {
        browserHook.findElementAndFillText(Constants.LOGIN_PAGE_EMAIL_ID, ConfigProperties.getPropertyValue("emailId"));
    }

    public void clickContinueButton() {
        browserHook.findElementAndClick(Constants.LOGIN_PAGE_CONTINUE_BUTTON);
    }

    public void enterPassword() {
        browserHook.findElementAndFillText(Constants.LOGIN_PAGE_PASSWORD, ConfigProperties.getPropertyValue("password"));
    }

    public void clickSubmitButton() {
        browserHook.findElementAndClick(Constants.LOGIN_PAGE_SUBMIT_BUTTON, true, true);
    }
}
