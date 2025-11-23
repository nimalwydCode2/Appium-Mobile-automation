package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class HomePage {
  private AppiumDriver driver;
  private WaitUtils wait;

  @AndroidFindBy(id = "com.demo:id/btnLogin")
  @iOSXCUITFindBy(accessibility = "login_button")
  public WebElement loginBtn;

  @AndroidFindBy(id = "com.example.app:id/username")
  @iOSFindBy(accessibility = "usernameField")
  private MobileElement usernameField;

  @AndroidFindBy(id = "com.example.app:id/password")
  @iOSFindBy(accessibility = "passwordField")
  private MobileElement passwordField;

  @AndroidFindBy(id = "com.example.app:id/loginBtn")
  @iOSFindBy(accessibility = "loginButton")
  private MobileElement loginButton;

  @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Login failed\")")
  @iOSFindBy(
      iOSClassChain = "**/XCUIElementTypeStaticText[`label == 'Login failed'`]")
  private MobileElement errorMessage;

  public HomePage(AppiumDriver driver) {
    this.driver = driver;
    this.wait = new WaitUtils(driver);
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  public void clickLogin() {
    wait.waitForVisibility(loginBtn).click();
  }
}
