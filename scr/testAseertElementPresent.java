import org.junit.Test;
import org.openqa.selenium.By;

public class testAseertElementPresent extends FirstTest {

    @Test
    public void testAseertElementPresented() {
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input Search Wikipedia", 5);

        MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java", "Cannot find search input", 5);

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@class='android.widget.TextView' and @text='Java (programming language)']"),
                "Cannot find 'Java article' in search", 10);
        MainPageObject.assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Cannot find tittle of article");

    }
}