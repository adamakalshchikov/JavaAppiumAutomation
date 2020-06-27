package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    // static values of locator
    private static final String SEARCH_INIT_ELEMENT_VALUE = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT_VALUE = "//*[contains(@text, 'Search…')]",
            SEARCH_CANCEL_BTN_VALUE = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_LOCATOR_VALUE = "//*[@resource-id='org.wikipedia:id/search_results_list']"
                + "/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_EMPTY_RESULT_ELEMENT_VALUE = "//*[@text='No results found']";

    // mutable values of locator
    private static String SEARCH_RESULT_VALUE = "//*[@resource-id='org.wikipedia:id/page_list_item_container']"
            + "//*[@text='{SUBSTRING}']";

    // constant locators
    private static final By SEARCH_INIT_ELEMENT = By.xpath(SEARCH_INIT_ELEMENT_VALUE),
            SEARCH_INPUT = By.xpath(SEARCH_INPUT_VALUE), SEARCH_CANCEL_BTN = By.id(SEARCH_CANCEL_BTN_VALUE),
            SEARCH_RESULT_LOCATOR = By.xpath(SEARCH_RESULT_LOCATOR_VALUE),
            SEARCH_EMPTY_RESULT_ELEMENT = By.xpath(SEARCH_EMPTY_RESULT_ELEMENT_VALUE);

    // mutable locators
    private static By SEARCH_RESULT_BY_SUBSTRING_TPL = By.xpath(SEARCH_RESULT_VALUE);

    // template methods
    private static void setResultSearchElement(String substring) {
        SEARCH_RESULT_VALUE = SEARCH_RESULT_VALUE.replace("{SUBSTRING}", substring);
        SEARCH_RESULT_BY_SUBSTRING_TPL = By.xpath(SEARCH_RESULT_VALUE);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click SEARCH_INIT element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking SEARCH_INIT_ELEMENT",
                5);
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, searchLine, "Cannot find and type into search line ", 5);
    }

    public void waitForSearchResult(String substring) {
        setResultSearchElement(substring);
        this.waitForElementPresent(SEARCH_RESULT_BY_SUBSTRING_TPL,
                "Cannot find search result with substring " + substring, 15);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BTN, "Cannot find search cancel button " + SEARCH_CANCEL_BTN, 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BTN, "Search cancel button is still present " + SEARCH_CANCEL_BTN,
                5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(SEARCH_CANCEL_BTN,
                "Cannot find and click search cancel button " + SEARCH_CANCEL_BTN, 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        setResultSearchElement(substring);
        this.waitForElementAndClick(SEARCH_RESULT_BY_SUBSTRING_TPL,
                "Cannot find and click searchresult with substring " + substring + SEARCH_RESULT_BY_SUBSTRING_TPL, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_LOCATOR,
                "Cannot find any results of search " + SEARCH_RESULT_LOCATOR, 15);

        int amountOfSearchResults = this.getAmountOfElements(SEARCH_RESULT_LOCATOR);
        return amountOfSearchResults;
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot find empty result element label " + SEARCH_EMPTY_RESULT_ELEMENT);
    }
}