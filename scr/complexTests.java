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

}