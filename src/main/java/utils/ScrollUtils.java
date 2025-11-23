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
        "new UiScrollable(new UiSelector().scrollable(true))"
        + ".scrollTextIntoView(\"" + text + "\")"));
  }
  // platform independent, but slower than platform specific scroll approach
  public void scrollToElement(MobileElement element) {
    int maxScrolls = 5;
    int count = 0;
    while (count < maxScrolls) {
      try {
        if (element.isDisplayed())
          break;
      } catch (Exception e) {
        swipeUp(); // your swipeUp method
      }
      count++;
    }
  }

  public void pressKeyCode(int keyCode) {
    driver.pressKey(new KeyEvent(keyCode));
  }
}
