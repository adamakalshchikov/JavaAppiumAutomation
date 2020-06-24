import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class FirstTest extends CoreTestCase {
        protected MainPageObject MainPageObject;

        protected void setUp() throws Exception
        {
                super.setUp();
                MainPageObject = new MainPageObject(driver);
        }

        @Test
        public void testSearch() {
                SearchPageObject searchPageObject = new SearchPageObject(driver);
                searchPageObject.initSearchInput();
                searchPageObject.typeSearchLine("Java");
                searchPageObject.waitForSearchResult("Object-oriented programming language");
        }

        @Test
        public void testCancelSearch() {
                MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_container"),
                                "Cannot find input 'Search Wikipedia'", 5);

                MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java",
                                "Cannot find search input", 5);

                MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "Cannot find search field", 5);

                MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Cannot find X cancel btn", 5);

                MainPageObject.waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "X is still present on the page",
                                5);
        }

        @Test
        public void testCompareArticleTitle() {
                MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                                "Cannot find input Search Wikipedia", 5);

                MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java",
                                "Cannot find search input", 5);

                MainPageObject.waitForElementAndClick(
                                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"
                                                + "//*[@text='Object-oriented programming language']"),
                                "Cannot find element", 5);

                WebElement articleTittleElement = MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                                "Article title not found", 15);

                String articleTittle = articleTittleElement.getAttribute("text");

                assertEquals("We see unexpected tittle" + articleTittle, "Java (programming language)", articleTittle);

        }
        }
