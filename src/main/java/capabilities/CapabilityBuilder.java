package capabilities;

import io.appium.java_client.remote.options.SupportsAppPackageOption;
import io.appium.java_client.remote.options.SupportsAppActivityOption;
import io.appium.java_client.remote.options.BaseOptions;

public
class CapabilityBuilder {
 public
  static BaseOptions < ? > getAndroidCapabilities() {
    BaseOptions < ? > caps = new BaseOptions<>();
    caps.setPlatformName("Android");
    caps.setDeviceName("Android Emulator");
    caps.setAutomationName("UiAutomator2");
    caps.setCapability("app",
                       System.getProperty("user.dir") + "/apps/demo.apk");
    caps.setCapability("autoGrantPermissions", true);
    return caps;
  }

 public
  static BaseOptions < ? > getIOSCapabilities() {
    BaseOptions < ? > caps = new BaseOptions<>();
    caps.setPlatformName("iOS");
    caps.setDeviceName("iPhone 14");
    caps.setAutomationName("XCUITest");
    caps.setCapability("bundleId", "com.example.demo");
    return caps;
  }
}
