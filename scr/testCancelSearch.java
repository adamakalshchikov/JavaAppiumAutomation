import org.junit.Test;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.WebElement;

public class testCancelSearch extends FirstTest {
    @Test
    public void testCancelSearchElement() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Cannot find input 'Search Wikipedia'", 5);

        MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "wiki", "Cannot find search line", 5);

        List<WebElement> elements = MainPageObject.waitForAllElements(By.id("org.wikipedia:id/page_list_item_container"), "No any elements found", 10);

        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Close btn not found", 5);

        MainPageObject.waitForElementsAreNotPresented(elements, "Elements aren't disappeared", 5);
    }
}