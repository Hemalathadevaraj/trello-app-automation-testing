package com.trello.assignment.pages;

import com.trello.assignment.browser.BrowserHook;

import static com.trello.assignment.browser.selenium.Constants.BOARDING_PAGE_BOARD_TITLE;

public class BoardPage {

    private final BrowserHook browserHook;

    public BoardPage(BrowserHook browserHook) {
        this.browserHook = browserHook;
    }

    public String getBoardTitle() {
        return browserHook.findElementAndGetText(BOARDING_PAGE_BOARD_TITLE);
    }
}
