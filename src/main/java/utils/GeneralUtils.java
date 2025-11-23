package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GeneralUtils {
  private AppiumDriver<MobileElement> driver;

  public GeneralUtils(AppiumDriver<MobileElement> driver) {
    this.driver = driver;
  }

  public String getCurrentActivity() {
    return driver.currentActivity();
  }

  // Drag and Drop
  public void dragAndDrop(MobileElement source, MobileElement target) {
    new TouchAction(driver)
        .press(ElementOption.element(source))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
        .moveTo(ElementOption.element(target))
        .release()
        .perform();
  }

  // Drag by Offset
  public void dragByOffset(MobileElement source, int xOffset, int yOffset) {
    new TouchAction(driver)
        .press(ElementOption.element(source))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
        .moveTo(ElementOption.point(xOffset, yOffset))
        .release()
        .perform();
  }

  // Open Notification Shade
  public void openNotifications() {
    driver.openNotifications();
  }

  // Switch to webview
  public void switchToWebView() {
    Set<String> contextNames = driver.getContextHandles();
    for (String contextName : contextNames) {
      if (contextName.contains("WEBVIEW")) {
        driver.context(contextName);
        break;
      }
    }
  }

  // Switch to Native view
  public void switchToNativeView() {
    Set<String> contextNames = driver.getContextHandles();
    for (String contextName : contextNames) {
      if (contextName.contains("NATIVE")) {
        driver.context(contextName);
        break;
      }
    }
  }

  // Switch to native view alternate way
  public void switchToNative() {
    driver.context("NATIVE_APP");
  }

  // Set Geo location
  public void setLocation(
      AppiumDriver driver, double latitude, double longitude, double altitude) {
    Location location = new Location(latitude, longitude, altitude);
    driver.setLocation(location);
  }
}
