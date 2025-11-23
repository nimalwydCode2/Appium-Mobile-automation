package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public
class WaitUtils {
 private
  AppiumDriver driver;

 public
  WaitUtils(AppiumDriver driver) { this.driver = driver; }

 public
  WebElement waitForVisibility(WebElement element) {
    return new WebDriverWait(driver, Duration.ofSeconds(20))
        .until(ExpectedConditions.visibilityOf(element));
  }
}
