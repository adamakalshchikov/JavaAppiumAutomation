import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class complexTests extends FirstTest {
    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input Search Wikipedia", 5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Appium", "Cannot find search input", 5);

        waitForElementAndClick(By.xpath("//*[@class='android.widget.TextView' and @text='Appium']"),
                "Cannot find 'Appium article' in search", 10);

        swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"), "Element not found");

    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input Search Wikipedia", 5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Cannot find search input", 5);

        waitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView' and @text='Java (programming language)']"),
                "Cannot find 'Java article' in search", 10);

        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Article title not found", 15);

        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find 'More options btn'", 5);

        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"), "Cannot find 'Add to reading list btn'",
                5);

        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"), "Cannot find 'Got it btn'", 5);

        waitForElementAndClear(By.id("org.wikipedia:id/text_input"), "Cannot find input for reading list name", 5);

        String nameofFolder = "Learning programming";

        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), nameofFolder,
                "Cannot find or send keys to input for reading list name", 5);

        waitForElementAndClick(By.xpath("//*[@class='android.widget.Button' and @text='OK']"),
                "Cannot find 'OK' button", 5);

        waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"), "Cannot find 'Close btn (NavigateUp)'", 5);

        waitForElementAndClick(By.xpath("//*[@content-desc='My lists' and @class='android.widget.FrameLayout']"),
                "Cannot find 'My lists btn'", 5);

        String template = "//*[@text='%s']";
        waitForElementAndClick(By.xpath(String.format(template, nameofFolder)),
                "Cannot find my list 'Learning programming'", 10);

        waitForElementPresent(By.xpath("//*[@text='Java (programming language)']"), "Cannot find element in my list", 10);

        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find element to swipe to left");

        waitForElementPresent(By.id("org.wikipedia:id/reading_list_empty_text"), "Artictle still presented in list");
    }

}