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

public
class AndroidUtils {
 private
  AndroidDriver<MobileElement> driver;

 public
  AndroidUtils(AppiumDriver<MobileElement> driver) {
    this.driver = (AndroidDriver<MobileElement>)driver;
  }

  // Android Key Events
 public
  void pressBack() { driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK)); }

 public
  void pressHome() { driver.pressKey(new KeyEvent().withKey(AndroidKey.HOME)); }

 public
  void pressAppSwitch() {
    driver.pressKey(new KeyEvent().withKey(AndroidKey.APP_SWITCH));
  }

  // Android Activity Management
 public
  void startActivity(String appPackage, String appActivity) {
    driver.startActivity(
        new io.appium.java_client.android.Activity(appPackage, appActivity));
  }
}
