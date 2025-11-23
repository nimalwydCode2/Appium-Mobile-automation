package utils;

import io.appium.java_client.AppiumDriver;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
  private AppiumDriver driver;

  public WaitUtils(AppiumDriver driver) {
    this.driver = driver;
  }

  public WebElement waitForVisibility(WebElement element) {
    return new WebDriverWait(driver, Duration.ofSeconds(20))
        .until(ExpectedConditions.visibilityOf(element));
  }
}
