package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ScrollUtils {

    private AppiumDriver driver;

    public ScrollUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    // Scroll until element is visible (Android)
    public void scrollToText(String text) {
        driver.findElement(By.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollTextIntoView(\"" + text + "\")"
        ));
    }
}
