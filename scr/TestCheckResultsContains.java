import org.junit.Test;
import org.openqa.selenium.By;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.WebElement;
import junit.framework.Assert;

public class TestCheckResultsContains extends FirstTest {
    @Test
    public void checkResultsContains() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Cannot find input 'Search Wikipedia'", 5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "wiki", "Cannot find search line", 5);

        List<WebElement> elements = waitForAllElements(By.id("org.wikipedia:id/page_list_item_title"),
                "No any elements found", 10);
        Iterator<WebElement> it = elements.listIterator();

        while (it.hasNext()) {
            WebElement element = it.next();

            String elementText = element.getAttribute("text").toLowerCase();
            assert (elementText.contains("wiki"));
        }
    }
}