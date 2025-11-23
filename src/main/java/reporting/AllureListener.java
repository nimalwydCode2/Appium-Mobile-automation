package reporting;

import base.BaseTest;
import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;
import io.appium.java_client.AppiumDriver;

public class AllureListener implements ITestListener {

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest) {
            AppiumDriver driver = ((BaseTest) currentClass).driver;
            byte[] screenshot = ScreenshotUtils.takeScreenshot(driver);
            saveScreenshot(screenshot);
        }
    }
}
