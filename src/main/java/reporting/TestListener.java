package reporting;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

import java.util.Base64;

public class TestListener implements ITestListener {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();
        if (instance instanceof BaseTest) {
            BaseTest base = (BaseTest) instance;
            byte[] screenshot = ScreenshotUtils.takeScreenshot(base.driver);
            String b64 = Base64.getEncoder().encodeToString(screenshot);
        }
    }
}
