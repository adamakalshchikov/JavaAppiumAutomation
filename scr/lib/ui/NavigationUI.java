package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    // constant locators values
    private static final String MY_LIST_BUTTON_VALUE = "//*[@content-desc='My lists' and @class='android.widget.FrameLayout']";

    // constant locators
    private static final By MY_LIST_BUTTON = By.xpath(MY_LIST_BUTTON_VALUE);


    public void clickMyLists() {
        this.waitForElementAndClick(MY_LIST_BUTTON, "Cannot find 'My lists btn'", 5);

    }
}