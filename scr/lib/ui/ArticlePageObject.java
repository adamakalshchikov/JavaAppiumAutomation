package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    // static values of locator
    private static String TITLE_VALUE = "org.wikipedia:id/view_page_title_text";


    // static locators
    private static By TITLE = By.id(TITLE_VALUE);
    
    public WebElement waitForTittleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article's title on page by " + TITLE, 15);
    }

    public String getArticleTitle() {
        WebElement titleElement = waitForTittleElement();
        return titleElement.getAttribute("text");
    }
    
}