import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import lib.ui.MainPageObject;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class FirstTest extends CoreTestCase {
        private MainPageObject MainPageObject;

        protected void setUp() throws Exception
        {
                super.setUp();
                MainPageObject = new MainPageObject(driver);
        }

        @Test
        public void testSearch() {
                waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                                "Cannot find input Search Wikipedia", 5);

                waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java",
                                "Cannot find search input", 5);

                WebElement element = waitForElementPresent(
                                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"
                                                + "//*[@text='Object-oriented programming language']"),
                                "Cannot find 'Object-oriented programming language'", 15);
        }

        @Test
        public void testCancelSearch() {
                waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                                "Cannot find input 'Search Wikipedia'", 5);

                waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java",
                                "Cannot find search input", 5);

                waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "Cannot find search field", 5);

                waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Cannot find X cancel btn", 5);

                waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "X is still present on the page",
                                5);
        }

        @Test
        public void testCompareArticleTitle() {
                waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                                "Cannot find input Search Wikipedia", 5);

                waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java",
                                "Cannot find search input", 5);

                waitForElementAndClick(
                                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"
                                                + "//*[@text='Object-oriented programming language']"),
                                "Cannot find element", 5);

                WebElement articleTittleElement = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                                "Article title not found", 15);

                String articleTittle = articleTittleElement.getAttribute("text");

                assertEquals("We see unexpected tittle" + articleTittle, "Java (programming language)", articleTittle);

        }
        }
