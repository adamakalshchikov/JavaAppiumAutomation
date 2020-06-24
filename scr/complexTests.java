import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import lib.ui.SearchPageObject;
import lib.ui.ArticlePageObject;

public class complexTests extends FirstTest {
        @Test
        public void testSwipeArticle() {
                SearchPageObject searchPageObject = new SearchPageObject(driver);
                searchPageObject.initSearchInput();
                searchPageObject.typeSearchLine("Appium");
                searchPageObject.clickByArticleWithSubstring("Appium");
                
                ArticlePageObject articlePageObject = new ArticlePageObject(driver);
                articlePageObject.waitForTittleElement();
                articlePageObject.swipeToFooter();
        }

        @Test
        public void testSaveFirstArticleToMyList() {
                MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                                "Cannot find input Search Wikipedia", 5);

                MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java",
                                "Cannot find search input", 5);

                MainPageObject.waitForElementAndClick(By
                                .xpath("//*[@class='android.widget.TextView' and @text='Java (programming language)']"),
                                "Cannot find 'Java article' in search", 10);

                MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"),
                                "Article title not found", 15);

                MainPageObject.waitForElementAndClick(
                                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                                "Cannot find 'More options btn'", 5);

                MainPageObject.waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                                "Cannot find 'Add to reading list btn'", 5);

                MainPageObject.waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                                "Cannot find 'Got it btn'", 5);

                MainPageObject.waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                                "Cannot find input for reading list name", 5);

                String nameofFolder = "Learning programming";

                MainPageObject.waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), nameofFolder,
                                "Cannot find or send keys to input for reading list name", 5);

                MainPageObject.waitForElementAndClick(By.xpath("//*[@class='android.widget.Button' and @text='OK']"),
                                "Cannot find 'OK' button", 5);

                MainPageObject.waitForElementAndClick(By.xpath("//*[@content-desc='Navigate up']"),
                                "Cannot find 'Close btn (NavigateUp)'", 5);

                MainPageObject.waitForElementAndClick(
                                By.xpath("//*[@content-desc='My lists' and @class='android.widget.FrameLayout']"),
                                "Cannot find 'My lists btn'", 5);

                String template = "//*[@text='%s']";
                MainPageObject.waitForElementAndClick(By.xpath(String.format(template, nameofFolder)),
                                "Cannot find my list 'Learning programming'", 10);

                MainPageObject.waitForElementPresent(By.xpath("//*[@text='Java (programming language)']"),
                                "Cannot find element in my list", 10);

                MainPageObject.swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
                                "Cannot find element to swipe to left");

                MainPageObject.waitForElementPresent(By.id("org.wikipedia:id/reading_list_empty_text"),
                                "Artictle still presented in list");
        }

        @Test
        public void testAmountOfNotEmptySearch() {
                String searchLine = "Linkin Park Discography";

                MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                                "Cannot find input Search Wikipedia", 5);

                MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), searchLine,
                                "Cannot find search input", 5);

                String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']"
                                + "/*[@resource-id='org.wikipedia:id/page_list_item_container']";
                MainPageObject.waitForElementPresent(By.xpath(searchResultLocator),
                                "Cannot find any results of search by " + searchLine, 15);

                int amountOfSearchResults = MainPageObject.getAmountOfElements(By.xpath(searchResultLocator));

                assertTrue("We found too few results", amountOfSearchResults > 0);

        }

        @Test
        public void testAmountOfEmptySearch() {
                String searchLine = "sdfa;wijw";

                MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                                "Cannot find input Search Wikipedia", 5);

                MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), searchLine,
                                "Cannot find search input", 5);

                String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']"
                                + "/*[@resource-id='org.wikipedia:id/page_list_item_container']";
                String emptyResultLabel = "//*[@text='No results found']";

                MainPageObject.waitForElementPresent(By.xpath(emptyResultLabel),
                                "Cannot find empty result labes" + searchLine, 15);

                MainPageObject.assertElementNotPresent(By.xpath(searchResultLocator),
                                "We've found some result by request");
        }

        @Test
        public void testChangeScreenOrientationOnSearchResult() {
                String searchLine = "Java";

                MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                                "Cannot find input Search Wikipedia", 5);

                MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), searchLine,
                                "Cannot find search input", 5);

                MainPageObject.waitForElementAndClick(
                                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']"
                                                + "//*[@text='Object-oriented programming language']"),
                                "Cannot find 'Object-oriented programming language' topic searching by", 15);

                String tittleBeforeRotation = MainPageObject.waitForElementAndGetAttribute(
                                By.id("org.wikipedia:id/view_page_title_text"), "text", "Cannot find article tittle",
                                15);

                driver.rotate(ScreenOrientation.LANDSCAPE);
                String tittleAfterRotation = MainPageObject.waitForElementAndGetAttribute(
                                By.id("org.wikipedia:id/view_page_title_text"), "text", "Cannot find article tittle",
                                15);

                assertEquals("Article tittle has been changed after screen rotation", tittleBeforeRotation,
                                tittleAfterRotation);

                driver.rotate(ScreenOrientation.PORTRAIT);
                String tittleAfterSecondRotation = MainPageObject.waitForElementAndGetAttribute(
                                By.id("org.wikipedia:id/view_page_title_text"), "text", "Cannot find article tittle",
                                15);

                assertEquals("Article tittle has been changed after screen rotation", tittleBeforeRotation,
                                tittleAfterSecondRotation);
        }

        @Test
        public void testCheckSearchArticleInBackground() {
                MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                                "Cannot find input Search Wikipedia", 5);

                MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Search…')]"), "Java",
                                "Cannot find search input", 5);

                MainPageObject.waitForElementPresent(By
                                .xpath("//*[@class='android.widget.TextView' and @text='Java (programming language)']"),
                                "Cannot find 'Java article' in search", 10);

                driver.runAppInBackground(2);

                MainPageObject.waitForElementPresent(By
                                .xpath("//*[@class='android.widget.TextView' and @text='Java (programming language)']"),
                                "Cannot find 'Java article' after returning from background", 10);

        }

}