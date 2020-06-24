import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;

import org.junit.Test;


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

                ArticlePageObject articlePageObject = new ArticlePageObject(driver);
                String articleTittle = articlePageObject.getArticleTitle();

                assertEquals("We see unexpected tittle" + articleTittle, "Java (programming language)", articleTittle);
        }
}
