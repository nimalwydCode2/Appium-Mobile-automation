package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public
class GeneralUtils {
 private
  AppiumDriver<MobileElement> driver;

 public
  GeneralUtils(AppiumDriver<MobileElement> driver) { this.driver = driver; }

 public
  void switchToWebView() {
    Set<String> contextNames = driver.getContextHandles();
    for (String contextName : contextNames) {
      if (contextName.contains("WEBVIEW")) {
        driver.context(contextName);
        break;
      }
    }
  }

 public
  String getCurrentActivity() { return driver.currentActivity(); }

  // Scroll Using UIAutomator
  // Scroll until element with text is visible
 public
  void scrollToText(String text) {
    driver.findElementByAndroidUIAutomator(
        "new UiScrollable(new UiSelector().scrollable(true))" +
        ".scrollTextIntoView(\"" + text + "\")");
  }

  // Drag and Drop
 public
  void dragAndDrop(MobileElement source, MobileElement target) {
    new TouchAction(driver)
        .press(ElementOption.element(source))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
        .moveTo(ElementOption.element(target))
        .release()
        .perform();
  }

  // Drag by Offset
 public
  void dragByOffset(MobileElement source, int xOffset, int yOffset) {
    new TouchAction(driver)
        .press(ElementOption.element(source))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
        .moveTo(ElementOption.point(xOffset, yOffset))
        .release()
        .perform();
  }

  // Open Notification Shade
 public
  void openNotifications() { driver.openNotifications(); }

 public
  void switchToWebView() {
    Set<String> contextNames = driver.getContextHandles();
    for (String contextName : contextNames) {
      if (contextName.contains("WEBVIEW")) {
        driver.context(contextName);
        break;
      }
    }
  }

 public
  void switchToNativeView() {
    Set<String> contextNames = driver.getContextHandles();
    for (String contextName : contextNames) {
      if (contextName.contains("NATIVE")) {
        driver.context(contextName);
        break;
      }
    }
  }

  // Or simply
 public
  void switchToNative() { driver.context("NATIVE_APP"); }
}
