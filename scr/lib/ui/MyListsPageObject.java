package lib.ui;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject {
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    // tpl locator's values
    private static String FOLDER_BY_NAME_TPL_VALUE = "//*[@text='%s']", ARTICLE_BY_TITLE_TPL_VALUE = "//*[@text='%s']";

    // tpl locators
    private static By FOLDER_BY_NAME_TPL, ARTICLE_BY_TITLE_TPL;

    // tpl setters
    private void setFolderName(String nameOfFolder) {
        String locatorValue = String.format(FOLDER_BY_NAME_TPL_VALUE, nameOfFolder);
        FOLDER_BY_NAME_TPL = By.xpath(locatorValue);
    }

    private void setArticleTitle(String articleTitle) {
        String locatorValue = String.format(ARTICLE_BY_TITLE_TPL_VALUE, articleTitle);
        ARTICLE_BY_TITLE_TPL = By.xpath(locatorValue);
    }

    public void openFolderByName(String nameOfFolder) {
        setFolderName(nameOfFolder);
        this.waitForElementAndClick(FOLDER_BY_NAME_TPL,
                "Cannot find folder by name " + nameOfFolder + FOLDER_BY_NAME_TPL, 5);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        setArticleTitle(articleTitle);
        this.waitForElementNotPresent(ARTICLE_BY_TITLE_TPL, "Article title is still located " + ARTICLE_BY_TITLE_TPL,
                5);

    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        setArticleTitle(articleTitle);
        this.waitForElementPresent(ARTICLE_BY_TITLE_TPL, "Cannot find saved article " + ARTICLE_BY_TITLE_TPL,
                5);
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);

        setArticleTitle(articleTitle);
        this.swipeElementToLeft(ARTICLE_BY_TITLE_TPL, "Cannot find saved article " + ARTICLE_BY_TITLE_TPL);
        this.waitForElementPresent(By.id("org.wikipedia:id/reading_list_empty_text"),
                "Artictle still presented in list");

        this.waitForArticleToDisappearByTitle(articleTitle);
    }
}