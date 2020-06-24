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
                SearchPageObject searchPageObject = new SearchPageObject(driver);
                searchPageObject.initSearchInput();
                searchPageObject.waitForCancelButtonToAppear();
                searchPageObject.clickCancelSearch();
                searchPageObject.waitForCancelButtonToDisappear();
                }

        @Test
        public void testCompareArticleTitle() {
                SearchPageObject searchPageObject = new SearchPageObject(driver);
                searchPageObject.initSearchInput();
                searchPageObject.typeSearchLine("Java");
                searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
                
                WebElement articleTittleElement = MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                                "Article title not found", 15);

                String articleTittle = articleTittleElement.getAttribute("text");

                assertEquals("We see unexpected tittle" + articleTittle, "Java (programming language)", articleTittle);

        }
        }
