import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class complexTests extends FirstTest {
    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input Search Wikipedia", 5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Cannot find search input", 5);

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"
                + "//*[@text='Object-oriented programming language']"), "Cannot find element", 5);
        
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
        swipeUp(2000);
    }

}