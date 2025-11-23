package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.Alert;

public class IOSUtils {
  private AppiumDriver<MobileElement> driver;

  public IOSUtils(AppiumDriver<MobileElement> driver) {
    this.driver = driver;
  }

  // KEYBOARD ACTIONS
  /**
   * Hide the keyboard if visible
   */
  public void hideKeyboard() {
    try {
      driver.hideKeyboard();
    } catch (Exception e) {
      System.out.println("Keyboard not visible: " + e.getMessage());
    }
  }

  /**
   * Press Return / Done key in input field
   * @param element The input field element
   */
  public void pressReturn(MobileElement element) {
    element.sendKeys("\n");
  }

  //  SYSTEM BUTTONS
  /**
   * Press Home button (iOS Simulator only)
   */
  public void pressHome() {
    Map<String, Object> args = new HashMap<>();
    args.put("name", "home");
    driver.executeScript("mobile: pressButton", args);
  }

  /**
   * Press Lock button (Simulator only)
   */
  public void pressLock() {
    Map<String, Object> args = new HashMap<>();
    args.put("name", "lock");
    driver.executeScript("mobile: pressButton", args);
  }

  /**
   * Press Volume Up button (Simulator only)
   */
  public void pressVolumeUp() {
    Map<String, Object> args = new HashMap<>();
    args.put("name", "volumeUp");
    driver.executeScript("mobile: pressButton", args);
  }

  /**
   * Press Volume Down button (Simulator only)
   */
  public void pressVolumeDown() {
    Map<String, Object> args = new HashMap<>();
    args.put("name", "volumeDown");
    driver.executeScript("mobile: pressButton", args);
  }

  //  SCROLL / SWIPE
  /**
   * Scroll down within a specific element (or screen if element null)
   * @param element scrollable element, or null to scroll whole screen
   */
  public void iosScrollDown(MobileElement element) {
    Map<String, Object> args = new HashMap<>();
    args.put("direction", "down");
    if (element != null)
      args.put("element", element.getId());
    driver.executeScript("mobile: scroll", args);
  }

  /**
   * Scroll up within a specific element (or screen if element null)
   */
  public void iosScrollUp(MobileElement element) {
    Map<String, Object> args = new HashMap<>();
    args.put("direction", "up");
    if (element != null)
      args.put("element", element.getId());
    driver.executeScript("mobile: scroll", args);
  }

  /**
   * Scroll until an element is visible (by checking isDisplayed)
   * @param targetElement element to scroll into view
   * @param maxScrolls max attempts
   */
  public void scrollToElement(MobileElement targetElement, int maxScrolls) {
    int count = 0;
    while (count < maxScrolls) {
      try {
        if (targetElement.isDisplayed())
          break;
      } catch (Exception e) {
        scrollDown(null); // scroll whole screen down
      }
      count++;
    }
  }

  public void handleIOSPermission(AppiumDriver driver) {
    try {
      Alert alert = driver.switchTo().alert(); // switch to system alert
      String alertText = alert.getText();
      System.out.println("Alert Text: " + alertText);
      alert.accept(); // Tap 'Allow'
      // alert.dismiss(); // Tap 'Don't Allow' if needed
    } catch (Exception e) {
      System.out.println("No permission alert displayed");
    }
  }
  // Toggle wifi in IOS
  // Open Settings app
  public void toggleWifiIos() {
    Map<String, Object> args = new HashMap<>();
    args.put("bundleId", "com.apple.Preferences"); // iOS Settings app
    driver.executeScript("mobile: launchApp", args);
    // Click Wi-Fi
    driver.findElement(MobileBy.AccessibilityId("Wi-Fi")).click();
    // Click toggle for your network (example)
    driver.findElement(MobileBy.AccessibilityId("MyNetwork")).click();
  }
}
