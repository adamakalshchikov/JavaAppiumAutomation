import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.net.URL;

public class FirstTest {
        private AppiumDriver driver;

        @Before
        public void setUp() throws Exception {
                DesiredCapabilities capabilities = new DesiredCapabilities();

                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("deviceName", "AndroidTestDevice");
                capabilities.setCapability("platformVersion", "8.0");
                capabilities.setCapability("automationName", "Appium");
                capabilities.setCapability("appPackage", "org.wikipedia");
                capabilities.setCapability("appActivity", ".main.MainActivity");
                capabilities.setCapability("app",
                                "C:\\Users\\a.damakalshchikov\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }

        @After
        public void tearDown() {
                driver.quit();
        }

        @Test
        public void firstTest() {
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
        public void compareArticleTitle() {
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

        private WebElement waitForElementPresent(By by, String error_message, long timeOutInSeconds) {
                WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
                wait.withMessage(error_message + "\n");
                return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }

        private WebElement waitForElementPresent(By by, String error_message) {
                return waitForElementPresent(by, error_message, 5);
        }

        private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds) {
                WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
                element.click();
                return element;
        }

        private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds) {
                WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
                element.sendKeys(value);
                return element;
        }

        private boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds) {
                WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
                wait.withMessage(error_message + "\n");
                return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }

        private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds) {
                WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
                element.clear();
                return element;
        }
}
