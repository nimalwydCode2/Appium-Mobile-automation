package utils;

import io.appium.java_client.AppiumDriver;
import java.util.Arrays;
import java.util.Collections;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;

public class GestureUtils {
  private AppiumDriver driver;
  public GestureUtils(AppiumDriver driver) {
    this.driver = driver;
  }

  // Swipe in any direction w3c
  public void swipe(int startX, int startY, int endX, int endY) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 1);
    swipe.addAction(finger.createPointerMove(
        Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
    swipe.addAction(
        finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    swipe.addAction(finger.createPointerMove(
        Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
    swipe.addAction(
        finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(Arrays.asList(swipe));
  }

  // Swipe using touch action, deprecated
  public void swipeOld(int startX, int startY, int endX, int endY) {
    new TouchAction(driver)
        .press(PointOption.point(startX, startY))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(SWIPE_DURATION)))
        .moveTo(PointOption.point(endX, endY))
        .release()
        .perform();
  }

  public void swipeUp() {
    swipe(500, 1500, 500, 300);
  }

  public void swipeDown() {
    swipe(500, 300, 500, 1500);
  }

  public void swipeLeft() {
    swipe(900, 800, 100, 800);
  }

  public void swipeRight() {
    swipe(100, 800, 900, 800);
  }

  // Tap action using w3c
  public void tap(MobileElement element) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence tap = new Sequence(finger, 1);
    // Get element center coordinates
    int x = element.getCenter().getX();
    int y = element.getCenter().getY();
    // Build sequence: move → press → release
    tap.addAction(finger.createPointerMove(
        Duration.ZERO, PointerInput.Origin.viewport(), x, y));
    tap.addAction(
        finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    tap.addAction(
        finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    // Perform the action
    driver.perform(Arrays.asList(tap));
  }

  // tap using touch action, deprecated
  public void tapElement(MobileElement element) {
    new TouchAction(driver).tap(ElementOption.element(element)).perform();
  }

  // Long press using w3c
  public void longPressW3C(MobileElement element, int durationSeconds) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence longPress = new Sequence(finger, 1);
    int x = element.getCenter().getX();
    int y = element.getCenter().getY();
    longPress.addAction(finger.createPointerMove(
        Duration.ZERO, PointerInput.Origin.viewport(), x, y));
    longPress.addAction(
        finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    longPress.addAction(
        finger.createPointerMove(Duration.ofSeconds(durationSeconds),
            PointerInput.Origin.viewport(), x, y));
    longPress.addAction(
        finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(Arrays.asList(longPress));
  }

  // Long press using Touch action
  public void longPressElement(MobileElement element, int durationSeconds) {
    new TouchAction(driver)
        .longPress(ElementOption.element(element))
        .waitAction(
            WaitOptions.waitOptions(Duration.ofSeconds(durationSeconds)))
        .release()
        .perform();
  }

  // Zoom in, w3c
  // Multi-touch: Pinch out
  public void zoomIn(Point center) {
    // Distance between the two fingers at the start
    int startOffset = 50;
    // Distance fingers will move outward
    int endOffset = 150;
    // Finger 1 (Top → Upwards)
    Point finger1Start = new Point(center.x, center.y - startOffset);
    Point finger1End = new Point(center.x, center.y - endOffset);
    // Finger 2 (Bottom → Downwards)
    Point finger2Start = new Point(center.x, center.y + startOffset);
    Point finger2End = new Point(center.x, center.y + endOffset);
    PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
    PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
    Sequence seq1 = new Sequence(finger1, 1);
    seq1.addAction(finger1.createPointerMove(Duration.ZERO,
        PointerInput.Origin.viewport(), finger1Start.x, finger1Start.y));
    seq1.addAction(
        finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    seq1.addAction(finger1.createPointerMove(Duration.ofMillis(300),
        PointerInput.Origin.viewport(), finger1End.x, finger1End.y));
    seq1.addAction(
        finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    Sequence seq2 = new Sequence(finger2, 1);
    seq2.addAction(finger2.createPointerMove(Duration.ZERO,
        PointerInput.Origin.viewport(), finger2Start.x, finger2Start.y));
    seq2.addAction(
        finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    seq2.addAction(finger2.createPointerMove(Duration.ofMillis(300),
        PointerInput.Origin.viewport(), finger2End.x, finger2End.y));
    seq2.addAction(
        finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    ((AppiumDriver) driver).perform(Arrays.asList(seq1, seq2));
  }

  // Zoom in, Touch action, deprecated
  public void zoomInTouchAction(int centerX, int centerY, int distance) {
    // Finger 1 moves up-left
    TouchAction finger1 =
        new TouchAction(driver)
            .press(PointOption.point(centerX, centerY - 10))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
            .moveTo(PointOption.point(centerX, centerY - distance))
            .release();
    // Finger 2 moves down-right
    TouchAction finger2 =
        new TouchAction(driver)
            .press(PointOption.point(centerX, centerY + 10))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
            .moveTo(PointOption.point(centerX, centerY + distance))
            .release();
    // Combine in MultiTouchAction
    new MultiTouchAction(driver).add(finger1).add(finger2).perform();
  }

  // Zoom out
  // Multi-touch: Pinch in
  public void zoomOut(Point center) {
    // Distance between the two fingers at the start (wider apart)
    int startOffset = 150;
    // Distance fingers move inward (toward center)
    int endOffset = 50;
    // Finger 1 (Top → Downwards)
    Point finger1Start = new Point(center.x, center.y - startOffset);
    Point finger1End = new Point(center.x, center.y - endOffset);
    // Finger 2 (Bottom → Upwards)
    Point finger2Start = new Point(center.x, center.y + startOffset);
    Point finger2End = new Point(center.x, center.y + endOffset);
    PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
    PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
    Sequence seq1 = new Sequence(finger1, 1);
    seq1.addAction(finger1.createPointerMove(Duration.ZERO,
        PointerInput.Origin.viewport(), finger1Start.x, finger1Start.y));
    seq1.addAction(
        finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    seq1.addAction(finger1.createPointerMove(Duration.ofMillis(300),
        PointerInput.Origin.viewport(), finger1End.x, finger1End.y));
    seq1.addAction(
        finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    Sequence seq2 = new Sequence(finger2, 1);
    seq2.addAction(finger2.createPointerMove(Duration.ZERO,
        PointerInput.Origin.viewport(), finger2Start.x, finger2Start.y));
    seq2.addAction(
        finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    seq2.addAction(finger2.createPointerMove(Duration.ofMillis(300),
        PointerInput.Origin.viewport(), finger2End.x, finger2End.y));
    seq2.addAction(
        finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    ((AppiumDriver) driver).perform(Arrays.asList(seq1, seq2));
  }

  // Zoom out touch action deprecated
  public void zoomOutTouchAction(int centerX, int centerY, int distance) {
    // Finger 1 moves downward toward center
    TouchAction finger1 =
        new TouchAction(driver)
            .press(PointOption.point(centerX, centerY - distance))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
            .moveTo(PointOption.point(centerX, centerY))
            .release();
    // Finger 2 moves upward toward center
    TouchAction finger2 =
        new TouchAction(driver)
            .press(PointOption.point(centerX, centerY + distance))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
            .moveTo(PointOption.point(centerX, centerY))
            .release();
    // Combine both fingers into a multi-touch action
    new MultiTouchAction(driver).add(finger1).add(finger2).perform();
  }
}
