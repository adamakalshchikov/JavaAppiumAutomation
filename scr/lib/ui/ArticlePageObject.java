package lib.ui;

import io.appium.java_client.AppiumDriver;
import sun.security.mscapi.KeyStore.MY;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    // static values of locator
    private static String TITLE_VALUE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT_VALUE = "//*[@text='View page in browser']",
            OPTIONS_BUTTON_VALUE = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON_VALUE = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY_VALUE = "org.wikipedia:id/onboarding_button",
            MY_LIST_INPUT_VALUE = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON_VALUE = "//*[@class='android.widget.Button' and @text='OK']";

    // static locators
    private static By TITLE = By.id(TITLE_VALUE), FOOTER_ELEMENT = By.xpath(FOOTER_ELEMENT_VALUE),
            OPTIONS_BUTTON = By.xpath(OPTIONS_BUTTON_VALUE),
            OPTIONS_ADD_TO_MY_LIST = By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON_VALUE),
            ADD_TO_MY_LIST_OVERLAY = By.id(ADD_TO_MY_LIST_OVERLAY_VALUE), MY_LIST_INPUT = By.id(MY_LIST_INPUT_VALUE),
            MY_LIST_OK_BUTTON = By.xpath(MY_LIST_INPUT_VALUE);

    public WebElement waitForTittleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article's title on page " + TITLE, 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTittleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article " + FOOTER_ELEMENT, 20);
    }

    public void addArticleToMyList(String nameOfFolder) {
        this.waitForElementAndClick(OPTIONS_BUTTON),
                "Cannot find 'More options btn'", 5);

        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST),
                "Cannot find 'Add to reading list btn'", 5);

        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY, "Cannot find 'Got it btn'", 5);

        this.waitForElementAndClear(MY_LIST_INPUT, "Cannot find input for reading list name", 5);

        this.waitForElementAndSendKeys(MY_LIST_INPUT, nameOfFolder,
                "Cannot find or send keys to input for reading list name", 5);

        this.waitForElementAndClick(MY_LIST_OK_BUTTON,
                "Cannot find 'OK' button", 5);
    }

}