package driver;

import capabilities.CapabilityBuilder;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.URL;

public class DriverFactory {
  public static AppiumDriver driver;

  public static AppiumDriver getDriver(String platform) throws Exception {
    if (platform.equalsIgnoreCase("android")) {
      driver = new AndroidDriver(new URL("http://0.0.0.0:4723"),
          CapabilityBuilder.getAndroidCapabilities());
    } else if (platform.equalsIgnoreCase("ios")) {
      driver = new IOSDriver(new URL("http://0.0.0.0:4723"),
          CapabilityBuilder.getIOSCapabilities());
    }
    return driver;
  }
}
