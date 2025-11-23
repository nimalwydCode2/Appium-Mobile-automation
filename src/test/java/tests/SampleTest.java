package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;

public class SampleTest extends BaseTest {
  @Test
  public void verifyLoginNavigation() {
    HomePage home = new HomePage(driver);
    home.clickLogin();
  }
}
