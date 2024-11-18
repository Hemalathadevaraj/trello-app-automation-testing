package com.trello.assignment.browser.selenium;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

import static com.trello.assignment.browser.selenium.Constants.BOARDING_PAGE_BOARD_TITLE;
import static com.trello.assignment.browser.selenium.Constants.HOME_PAGE_BOARD_TITLE;
import static com.trello.assignment.browser.selenium.Constants.HOME_PAGE_CREATEBOARD_MENU;
import static com.trello.assignment.browser.selenium.Constants.HOME_PAGE_CREATEBOARD_MENU_BUTTON;
import static com.trello.assignment.browser.selenium.Constants.HOME_PAGE_CREATE_BUTTON;
import static com.trello.assignment.browser.selenium.Constants.HOME_PAGE_VISIBILITY;
import static com.trello.assignment.browser.selenium.Constants.LOGIN_PAGE_CONTINUE_BUTTON;
import static com.trello.assignment.browser.selenium.Constants.LOGIN_PAGE_EMAIL_ID;
import static com.trello.assignment.browser.selenium.Constants.LOGIN_PAGE_LOGIN_BUTTON;
import static com.trello.assignment.browser.selenium.Constants.LOGIN_PAGE_PASSWORD;
import static com.trello.assignment.browser.selenium.Constants.LOGIN_PAGE_SUBMIT_BUTTON;

public class PageElementsUtils {

    private final Map<String, By> webElementsMap = new HashMap<>();

    public PageElementsUtils() {

        // Add Elements to be used by Pages
        By loginButton = By.linkText("Log in");
        By emailId = By.xpath("//input[@id='username']");
        By continueButton = By.xpath("//button[@id='login-submit']");
        By password = By.xpath("//input[@name='password']");
        By submitButton = By.xpath("//button[@type='submit']");
        By createButton = By.xpath("//button[@data-testid='header-create-menu-button']");
        By createBoard = By.xpath("//button[@data-testid='header-create-board-button']");
        By boardTitle = By.xpath("//div[text()='Board title']//following::input[1]");
        By visibilityDropdown = By.xpath("//div[@data-testid='create-board-select-visibility']");
        By boardCreateButton = By.xpath("//button[@data-testid='create-board-submit-button']");
        By boardPageBoardTitle = By.xpath("//h1[@data-testid='board-name-display']");
        webElementsMap.put(LOGIN_PAGE_LOGIN_BUTTON, loginButton);
        webElementsMap.put(LOGIN_PAGE_EMAIL_ID, emailId);
        webElementsMap.put(LOGIN_PAGE_CONTINUE_BUTTON, continueButton);
        webElementsMap.put(LOGIN_PAGE_PASSWORD, password);
        webElementsMap.put(LOGIN_PAGE_SUBMIT_BUTTON, submitButton);
        webElementsMap.put(HOME_PAGE_CREATE_BUTTON, createButton);
        webElementsMap.put(HOME_PAGE_CREATEBOARD_MENU, createBoard);
        webElementsMap.put(HOME_PAGE_BOARD_TITLE, boardTitle);
        webElementsMap.put(HOME_PAGE_VISIBILITY, visibilityDropdown);
        webElementsMap.put(HOME_PAGE_CREATEBOARD_MENU_BUTTON, boardCreateButton);
        webElementsMap.put(BOARDING_PAGE_BOARD_TITLE, boardPageBoardTitle);
    }

    public By findElementById(String elementId) {
        return webElementsMap.get(elementId);
    }
}
