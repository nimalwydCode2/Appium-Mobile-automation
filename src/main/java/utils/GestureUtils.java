package utils;

import io.appium.java_client.AppiumDriver;

import java.util.Arrays;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;

public class GestureUtils {

    private AppiumDriver driver;
    public GestureUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    // Swipe in any direction
    public void swipe(int startX, int startY, int endX, int endY) {
        driver.perform(
                driver.createPointerAction()
                        .press(startX, startY)
                        .waitAction(300)
                        .move(endX, endY)
                        .release()
        );
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

    // Tap action
    public void tap(int x, int y) {
        driver.perform(driver.createPointerAction().tap(x, y));
    }

    // Long press
    public void longPress(int x, int y, int durationMs) {
        driver.perform(
                driver.createPointerAction()
                        .press(x, y)
                        .waitAction(durationMs)
                        .release()
        );
    }

    // Zoom in
    // Multi-touch: Pinch out
    public void zoomIn(Point center) {
    // Distance between the two fingers at the start
    int startOffset = 50;

    // Distance fingers will move outward
    int endOffset = 150;

    // Finger 1 (Top → Upwards)
    Point finger1Start = new Point(center.x, center.y - startOffset);
    Point finger1End   = new Point(center.x, center.y - endOffset);

    // Finger 2 (Bottom → Downwards)
    Point finger2Start = new Point(center.x, center.y + startOffset);
    Point finger2End   = new Point(center.x, center.y + endOffset);

    PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
    PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

    Sequence seq1 = new Sequence(finger1, 1);
    seq1.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), finger1Start.x, finger1Start.y));
    seq1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    seq1.addAction(finger1.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), finger1End.x, finger1End.y));
    seq1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    Sequence seq2 = new Sequence(finger2, 1);
    seq2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), finger2Start.x, finger2Start.y));
    seq2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    seq2.addAction(finger2.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), finger2End.x, finger2End.y));
    seq2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    ((AppiumDriver) driver).perform(Arrays.asList(seq1, seq2));
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
    Point finger1End   = new Point(center.x, center.y - endOffset);

    // Finger 2 (Bottom → Upwards)
    Point finger2Start = new Point(center.x, center.y + startOffset);
    Point finger2End   = new Point(center.x, center.y + endOffset);

    PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
    PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

    Sequence seq1 = new Sequence(finger1, 1);
    seq1.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), finger1Start.x, finger1Start.y));
    seq1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    seq1.addAction(finger1.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), finger1End.x, finger1End.y));
    seq1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    Sequence seq2 = new Sequence(finger2, 1);
    seq2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), finger2Start.x, finger2Start.y));
    seq2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
    seq2.addAction(finger2.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), finger2End.x, finger2End.y));
    seq2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

    ((AppiumDriver) driver).perform(Arrays.asList(seq1, seq2));
}

}
