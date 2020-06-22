import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class searchStringTest extends FirstTest {
    @Test
    public void testSearchStringPresented() {
        MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Cannot find input 'Search Wikipedia'", 5);

        WebElement searchStringElement = MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/search_src_text"), "Element search_src_text isn't present", 5);
        String searchText = searchStringElement.getAttribute("text");

        assertEquals("earch…", searchText);
       }
}