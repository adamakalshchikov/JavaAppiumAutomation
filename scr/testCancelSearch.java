import org.junit.Test;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.WebElement;

public class testCancelSearch extends FirstTest {
    @Test
    public void testCancelSearchElement() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Cannot find input 'Search Wikipedia'", 5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "wiki", "Cannot find search line", 5);

        List<WebElement> elements =  waitForAllElements(By.id("org.wikipedia:id/page_list_item_container"), "No any elements found", 10);

        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Close btn not found", 5);

        waitForElementsAreNotPresented(elements, "Elements aren't disappeared", 5);
    }
}