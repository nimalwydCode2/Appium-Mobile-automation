package base;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.*;

public
class BaseTest {
 public
  AppiumDriver driver;

  @BeforeClass @Parameters({"platform"}) public void setup(
      @Optional("android") String platform) throws Exception {
    driver = DriverFactory.getDriver(platform);
  }

  @AfterClass public void teardown() {
    if (driver != null) driver.quit();
  }
}
