package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;

import org.openqa.selenium.By;
import java.time.Duration;

public class AndroidUtils {

    private AndroidDriver<MobileElement> driver;

    public AndroidUtils(AppiumDriver<MobileElement> driver) {
        this.driver = (AndroidDriver<MobileElement>) driver;
    }

    // ===== Android Key Events =====
    public void pressBack() {
        driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
    }

    public void pressHome() {
        driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME));
    }

    public void pressAppSwitch() {
        driver.pressKey(new KeyEvent().withKey(AndroidKey.APP_SWITCH));
    }

    // ===== Open Notification Shade =====
    public void openNotifications() {
        driver.openNotifications();
    }

    // ===== Android Activity Management =====
    public void startActivity(String appPackage, String appActivity) {
        driver.startActivity(new io.appium.java_client.android.Activity(appPackage, appActivity));
    }

    public String getCurrentActivity() {
        return driver.currentActivity();
    }

    // ===== Scroll Using UIAutomator =====
    // Scroll until element with text is visible
    public void scrollToText(String text) {
        driver.findElementByAndroidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollTextIntoView(\"" + text + "\")"
        );
    }

    // ===== Drag and Drop =====
    public void dragAndDrop(MobileElement source, MobileElement target) {
        new TouchAction(driver)
            .press(ElementOption.element(source))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(ElementOption.element(target))
            .release()
            .perform();
    }

    // ===== Drag by Offset =====
    public void dragByOffset(MobileElement source, int xOffset, int yOffset) {
        new TouchAction(driver)
            .press(ElementOption.element(source))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
            .moveTo(ElementOption.point(xOffset, yOffset))
            .release()
            .perform();
    }
}
