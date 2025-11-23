package tests;

import base.BaseTest;
import pages.HomePage;
import org.testng.annotations.Test;

public
class SampleTest extends BaseTest {
  @Test public void verifyLoginNavigation() {
    HomePage home = new HomePage(driver);
    home.clickLogin();
  }
}
