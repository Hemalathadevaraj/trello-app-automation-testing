package com.trello.assignment.pages;

import com.trello.assignment.browser.BrowserHook;
import com.trello.assignment.browser.selenium.Constants;

public class HomePage {

    private final BrowserHook browserHook;

    public HomePage(BrowserHook browserHook) {
        this.browserHook = browserHook;
    }

    public void clickCreateButton() {
        browserHook.findElementAndClick(Constants.HOME_PAGE_CREATE_BUTTON, true, true);
    }

    public void clickOnCreateBoardOnMenu() {
        browserHook.findElementAndClick(Constants.HOME_PAGE_CREATEBOARD_MENU);
    }

    public void clickOnCreateButtonFromMenu() {
        browserHook.findElementAndClick(Constants.HOME_PAGE_CREATEBOARD_MENU_BUTTON, true, true);
    }

    public void fillBoardTitle(String boardName) {
        browserHook.findElementAndFillText(Constants.HOME_PAGE_BOARD_TITLE, boardName);
    }

    public boolean isCreateBoardButtonEnabled() {
        return browserHook.findElementAndCheckIsEnabled(Constants.HOME_PAGE_CREATEBOARD_MENU_BUTTON);
    }
}
