package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public
class ScreenshotUtils {
 public
  static byte[] takeScreenshot(AppiumDriver driver) {
    try {
      return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    } catch (Exception e) {
      return new byte[0];
    }
  }
}
