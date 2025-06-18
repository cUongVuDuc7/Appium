package keyword;

import driver.DriverManager;
import helpers.LogHelper;
import helpers.PropertiesFile;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.Setting;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.testng.Assert;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class KeywordWeb {
    private static Logger logger = LogHelper.getLogger();
    public static Random rd = new Random();
    public KeywordWeb() {
    }
    public void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void setSetting(int time) {
        logger.info("setSetting");
        DriverManager.getDriver().setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, time);  // 10000 Đảm bảo UI ổn định trước khi tìm phần tử
        DriverManager.getDriver().setSetting(Setting.WAIT_SCROLL_ACKNOWLEDGMENT_TIMEOUT, 100);  // Đảm bảo hành động cuộn hoàn tất trước khi tiếp tục
    }
    public int getWidthDevice(){
        int width = DriverManager.getDriver().manage().window().getSize().getWidth();
        System.out.println("Device Width: " + DriverManager.getDriver().manage().window().getSize().getWidth());
        return width;
    }
    public int getHeightDevice(){
        int height = DriverManager.getDriver().manage().window().getSize().getHeight();
        System.out.println("Device Height: " + DriverManager.getDriver().manage().window().getSize().getHeight());
        return height;
    }
    @Step("Nhập giá trị: {0}  text: {1}")
    public void sendKeys(By by, String key){
        webDriverWaitForElementPresent(by, 10);
        String content = PropertiesFile.getPropValue(key);
        logger.info("Send key " + by);
        if (content == null) {
            content = key;
        }
        DriverManager.getDriver().findElement(by).sendKeys(content);
    }
    @Step("Nhập giá trị: {0}  text: {1}")
    public void pressKeyNumber(String key){
        logger.info("Press key number");
        switch (key) {
            case "1":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.DIGIT_1));
                break;
            case "2":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.DIGIT_2));
                break;
            case "3":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.DIGIT_3));
                break;
            case "4":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.DIGIT_4));
                break;
            case "5":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.DIGIT_5));
                break;
            case "6":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.DIGIT_6));
                break;
            case "7":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.DIGIT_7));
                break;
            case "8":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.DIGIT_8));
                break;
            case "9":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.DIGIT_9));
                break;
            case "0":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.DIGIT_0));
                break;
            case "escape":
                DriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.ESCAPE));
                sleep(0.5);
                break;
        }
    }
    @Step("Cuộn tới vị trí: {0}")
    public void scrollToPositionByScript(String jsScript) {
        logger.info(" scrolling to position ");
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript(jsScript);
    }
    @Step("Lấy giá trị: {0}")
    public String getText(By by){
        logger.info("Get Text " + by);
        return DriverManager.getDriver().findElement(by).getText();
    }
    @Step("Click: {0}")
    public void click(By by){
        logger.info("click " + by);
        sleep(0.1);
        DriverManager.getDriver().findElement(by).click();
    }
    @Step("So sánh message: {1}")
    public void assertEqual(By by, String text){
        String content = PropertiesFile.getPropValue(text);
        logger.info("Compare message" + by + " with " + text);
        if (content == null) {
            content = text;
        }
        Assert.assertEquals(DriverManager.getDriver().findElement(by).getText(), content);
    }
    @Step("So sánh data: {0} với giá trị: {1}")
    public void assertEqualData(String db, String expect){
        logger.info("Compare message: " + db + " with " + expect);
        Assert.assertEquals(db, expect);
    }
    @Step("So sánh data: {0} với giá trị: {1}")
    public void assertEqualMultiData(String db, String expect){
        logger.info("Compare message: " + db + " with " + expect);
        String[] arrDb = db.split(",");
        String[] arrExp = db.split(",");
        for (int i = 0; i < arrExp.length; i++) {
            Assert.assertEquals(arrDb[i], arrExp[i]);
        }
    }
    @Step("Assert true {0} và {1}")
    public void containsTrue(String actual, String expect){
        logger.info("Compare true: " + actual + " with " + expect);
        Assert.assertTrue(actual.contains(expect));
    }
    @Step("Assert true {0}")
    public void assertTrue(boolean condition) {
        logger.info("Compare True" + condition);
        Assert.assertTrue(condition);
    }
    @Step("Chờ element hiển thị: {0}")
    public void webDriverWaitForElementPresent(By by, long timeout){
        logger.info("Wait For Element Present" + by);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    @Step("Chờ element không hiển thị: {0}")
    public void webDriverWaitInvisibleElement(By by, long timeout){
        logger.info("Wait For Element Not Present" + by);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    @Step("Xác thực hiển thị and click: {0}")
    public boolean verifyPresentAndClick(By by){
        boolean check = false;
        logger.info("Verify Present And Click");
        if(verifyElementPresent(by)) {
            click(by);
            check = true;
        }
        return check;
    }
    public boolean verifyElementPresent(By by) {
        logger.info("verifyElementPresent: " + by);
        try {
            DriverManager.getDriver().findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Step("Lấy danh sách element: {0}")
    public List<WebElement> getListElement(By by) {
        logger.info("get list element: " + by);
        webDriverWaitForElementPresent(by, 10);
        return DriverManager.getDriver().findElements(by);
    }
    @Step("Chọn từ drop list: {1}")
    public void selectByText(By by, String text){
        logger.info("Select By Text " + by);
        Select singleSelect = new Select(DriverManager.getDriver().findElement(by));
        singleSelect.selectByVisibleText(text);
    }
    @Step("Xóa: {0} và nhập giá trị {1}")
    public void clearTextAndSendKey(By by, String contents){
        logger.info("Clear and send keys" + by + "with " + contents);
        webDriverWaitForElementPresent(by, 5);
        String content = PropertiesFile.getPropValue(contents);
        if (content == null) {
            content = contents;
        }
        clearText(by);
        sendKeys(by, content);
    }
    @Step("Xóa giá trị: {0}")
    public void clearText(By by) {
        logger.info("clearText");
        DriverManager.getDriver().findElement(by).clear();
    }
    @Step("Xác thực element có thể tương tác: {0}")
    public boolean isElementEnable(By by) {
        logger.info("verifyElementPresent: " + by);
        try {
            DriverManager.getDriver().findElement(by).isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Step("Lấy ngẫu nhiên element: {0}")
    public void randomElement(String element) {
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        List<WebElement> weblist = DriverManager.getDriver().findElements(By.xpath(xPathElement));
        int size = weblist.size();
        int randNumber = ThreadLocalRandom.current().nextInt(0, size);
        weblist.get(randNumber).click();
    }

    public int randomNumber(int size) {
        int randNumber = ThreadLocalRandom.current().nextInt(1, size);
        return randNumber;
    }
    public int randomNumberFromTo(int from, int to) {
        int randNumber = ThreadLocalRandom.current().nextInt(from, to);
        return randNumber;
    }
    @Step("Double click: {0}")
    public void doubleClick(String element) {
        logger.info("double click" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        Actions builder = new Actions(DriverManager.getDriver());
        WebElement elementRep = DriverManager.getDriver().findElement(By.xpath(xPathElement));
        builder.doubleClick(elementRep).perform();
    }
    @Step("Xác thực element hiển thị: {0}")
    public boolean isDisplayElement(By by) {
        logger.info("Check element display " + by );
        boolean stt = DriverManager.getDriver().findElement(by).isDisplayed();
        return stt;
    }
    public String getPosition(By element){
        WebElement nameField = DriverManager.getDriver().findElement(element);
        Point position = nameField.getLocation();
        System.out.println("Position:   " + position.getX() + "," + position.getY());
        return position.getX() + "," + position.getY();
    }
    public String getPageSource(){
        return DriverManager.getDriver().getPageSource();
    }
    public void scrollByCoordinates(double yStart, double yEnd) {
        logger.info("scroll From "+ yStart   + " To " + yEnd);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);
        int centerX = DriverManager.getDriver().manage().window().getSize().width / 2;
        int start = (int) (DriverManager.getDriver().manage().window().getSize().height * yStart);
        int end = (int) (DriverManager.getDriver().manage().window().getSize().height * yEnd);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, start));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), centerX, end));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getDriver().perform(Collections.singletonList(swipe));
        untilJqueryIsDone(50L);
    }
    public void scrollFromElementTo(By fromElement, int xEnd, int yEnd) {
        logger.info("scroll From Element To " + fromElement );
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scrollSequence = new Sequence(finger, 0);
        Rectangle rect = DriverManager.getDriver().findElement(fromElement).getRect();
        int xStart = rect.getX();
        int yStart = rect.getY();
        logger.info("x " + xStart + "y " + yStart);
        scrollSequence.addAction(finger.createPointerMove(
                Duration.ofMillis(0),
                PointerInput.Origin.viewport(),
                xStart, yStart
        ));
        scrollSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scrollSequence.addAction(finger.createPointerMove(
                Duration.ofMillis(200),
                PointerInput.Origin.viewport(),
                xEnd, yEnd
        ));
        scrollSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getDriver().perform(Collections.singletonList(scrollSequence));
    }
    @Step("swipe element")
    public void swipe(By element, String flag) {
        webDriverWaitForElementPresent(element,10);
        Rectangle rect = DriverManager.getDriver().findElement(element).getRect();
        int centerX = rect.getX() + (rect.getWidth() / 2);
        int centerY = rect.getY() + (rect.getHeight() / 2);
        logger.info("centerX " + centerX + "centerY " + centerY);
        int endX = 0;
        int endY = 0;
        if (flag.equals("up")) {
            endX = centerX;
            endY = rect.y + 5;
        }
        else if (flag.equals("down")) {
            endX = centerX;
            endY = rect.y + (rect.getHeight() - 5);
        }
        else if (flag.equals("left")) {
            endX = rect.x + 5;
            endY = centerY;
        }
        else {
            endX = rect.x + (rect.getWidth() - 5);
            endY = centerY;
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tapSeq = new Sequence(finger, 1);
        tapSeq.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, centerY));
        tapSeq.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        tapSeq.addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), endX, endY));
        tapSeq.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        DriverManager.getDriver().perform(Arrays.asList(tapSeq));
    }
    @Step("tap element")
    public  void tapElement(By element) {
        webDriverWaitForElementPresent(element,10);
        Rectangle rec = DriverManager.getDriver().findElement(element).getRect();
        int centerX = rec.getX() + (rec.getWidth() / 2);
        int centerY = rec.getY() + (rec.getHeight() / 2);
        Point elementLocation = new Point(centerX, centerY);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), elementLocation.getX(), elementLocation.getY()));
        tap.addAction(finger.createPointerDown(0));
        tap.addAction(finger.createPointerUp(0));
        DriverManager.getDriver().perform(Collections.singletonList(tap));
    }

    @Step("tap màn hình") // sủ dụng dc cho ios
    public void tap(By by){
        webDriverWaitForElementPresent(by, 10);
        Point location = DriverManager.getDriver().findElement(by).getLocation();
        Map<String, Object> args = new HashMap<>();
        args.put("element",((RemoteWebElement) DriverManager.getDriver().findElement(by)).getId());
        args.put("x", location.getX());
        args.put("y", location.getY());
        DriverManager.getDriver().executeScript("mobile: tap", args);
        // executeScript("mobile: doubleTap", args);
        // executeScript("mobile: twoFingerTap", args);
        // executeScript("mobile: touchAndHold", args);
    }
    @Step("Lấy giá trị alert: {0}")
    public String getAlertText() {
        logger.info("Getting alert text...");
        Alert alert = DriverManager.getDriver().switchTo().alert();
        return alert.getText();
    }
    @Step("Chuyển sang web context: {0}")
    public boolean switchToWebContext() {
        ArrayList<String> contexts = new ArrayList<>(DriverManager.getDriver().getContextHandles());
        for (String context : contexts) {
            System.out.println(context);
            if(context.contains("WEBVIEW")){   // NATIVE_APP
                DriverManager.getDriver().context(context);
                return true;
            }
        }
        return false;
    }
    public void addDataToList(List<String> list,String element){
        String data = PropertiesFile.getPropValue(element);
        if (data == null) {
            data = element;
        }
        list.add(data);
    }
    public void waitPageLoad(){
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }
    public void dragAndDropToObj(String startElement, String endElement) {
        logger.info("drag from" + startElement + " to" + endElement);
        String xPathElement1 = PropertiesFile.getPropValue(startElement);
        if (xPathElement1 == null) {
            xPathElement1 = startElement;
        }
        String xPathElement2 = PropertiesFile.getPropValue(endElement);
        if (xPathElement2 == null) {
            xPathElement2 = endElement;
        }
        Actions builder = new Actions(DriverManager.getDriver());
        WebElement source = DriverManager.getDriver().findElement(By.xpath(xPathElement1));
        WebElement target = DriverManager.getDriver().findElement(By.xpath(xPathElement2));
        builder.dragAndDrop(source, target).perform();
    }
    public void rightClick(String element, String menuItem) {
        logger.info("rightClick" + element);
        String xPathElement1 = PropertiesFile.getPropValue(element);
        if (xPathElement1 == null) {
            xPathElement1 = element;
        }
        String xPathElement2 = PropertiesFile.getPropValue(menuItem);
        if (xPathElement2 == null) {
            xPathElement2 = menuItem;
        }
        Actions builder = new Actions(DriverManager.getDriver());
        WebElement clickMe = DriverManager.getDriver().findElement(By.xpath(xPathElement1));
        WebElement editMenuItem = DriverManager.getDriver().findElement(By.xpath(xPathElement2));
        builder.contextClick(clickMe).moveToElement(editMenuItem).click().perform();
    }
    public void hoverAndClick(String element) {
        logger.info("Move To Element" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        Actions action = new Actions(DriverManager.getDriver());
        WebElement elementRep = DriverManager.getDriver().findElement(By.xpath(xPathElement));
        action.moveToElement(elementRep).perform();
    }
    public void hoverAndClicks(String element) {
        logger.info("Move To Element" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        Actions action = new Actions(DriverManager.getDriver());
        WebElement elementRep = DriverManager.getDriver().findElement(By.xpath(xPathElement));
        action.moveToElement(elementRep).clickAndHold();
    }

    public void executeJavaScript(String command) {
        logger.info("Executing JavaScript");
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript(command);
    }
    public void acceptAlert() {
        logger.info("Accepting alert...");
        Alert alert = DriverManager.getDriver().switchTo().alert();
        alert.accept();
    }
    public void switchToIFrameByXpath(String element) {
        logger.info("Switching to Iframe");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement iframe = DriverManager.getDriver().findElement(By.xpath(xPathElement));
        DriverManager.getDriver().switchTo().frame(iframe);
    }
    public void scrollDownToElement(By xPath) {
        logger.info("scrollDownToElement" + xPath);
        WebElement element = DriverManager.getDriver().findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"PUBG\"))"));
        element.click();

    }
    public void scrollDownToElementByCss(String element) {
        logger.info("scroll to element");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement elements = DriverManager.getDriver().findElement(By.cssSelector(xPathElement));
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(elements);
        actions.perform();
    }
    public void selectDropDownListByName(String ddlPath, String itemName) {
        logger.info("select item by visibe text");
        String xPathElement1 = PropertiesFile.getPropValue(ddlPath);
        if (xPathElement1 == null) {
            xPathElement1 = ddlPath;
        }
        String xPathElement2 = PropertiesFile.getPropValue(itemName);
        if (xPathElement2 == null) {
            xPathElement2 = itemName;
        }
        Select dropDownList = new Select(DriverManager.getDriver().findElement(By.xpath(xPathElement1)));
        dropDownList.selectByVisibleText(xPathElement2);
    }
    public void verifyElementDisplay(By element) {
        logger.info("verifyElement " + element);
        DriverManager.getDriver().findElement(element).isDisplayed();
    }

    public void verifyElementNotDisplayed(By element) {
        logger.info("checkElementVisibleOrNot" + element);
        boolean confirm = true;
        try {
            DriverManager.getDriver().findElement(element);
            confirm = false;
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(confirm);
    }
    public boolean elementIsDisplayed(By element) {
        logger.info("element Is Displayed " + element);
        try {
            DriverManager.getDriver().findElement(element);
            return true;
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkStatusIsDisplay(String element) {
        logger.info("Check status ");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        boolean status = DriverManager.getDriver().findElement(By.xpath(xPathElement)).isDisplayed();
        if (status) {
            System.out.println("Is Display" + "\t" + element);
        } else {
            System.out.println("Is not Display" + "\t" + element);
        }
        return status;

    }
    public void waitForAjaxToFinish() throws InterruptedException {
        logger.info("waitForAjaxToFinish");
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(3000));
        wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript(
                "return !!window.jQuery && !!window.jQuery.active == 0;").equals(true));
        Thread.sleep(150);
    }

    private static void until(Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));
//        webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    public void untilJqueryIsDone(Long timeoutInSeconds){
        until((d) ->
        {
            Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone) logger.info("JQuery call is in Progress");
            return isJqueryCallDone;
        }, timeoutInSeconds);
    }

    public String waitForElementNotVisible(int timeOutInSeconds, String elementXPath) {
        logger.info("elemnt " + elementXPath + " not visible");
        String xPathElement = PropertiesFile.getPropValue(elementXPath);
        if (xPathElement == null) {
            xPathElement = elementXPath;
        }
        try {
            (new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOutInSeconds))).until(ExpectedConditions.invisibilityOfElementLocated(By
                    .xpath(xPathElement)));
            return null;
        } catch (TimeoutException e) {
            return "Build your own errormessage...";
        }
    }
    public void webDriverWaitForElementPresentByCss(String element, long timeout) {
        logger.info("webDriverWaitForElementPresentByCss");
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(xPathElement)));
    }
    public void fluentWaitForElementPresent(String element, Duration polling, Duration timeout) {
        logger.info("fluentWaitForElementPresent");
        Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverManager.getDriver())
                .withTimeout(timeout)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
    }
    public String getAttribute(String element, String tag) {
        logger.info("get Attribute of" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement b = DriverManager.getDriver().findElement(By.xpath(xPathElement));
        String c = b.getAttribute(tag);
        logger.info(c);
        return c;
    }

    public String getAttributeWithValue(String element) {
        logger.info("get Attribute of" + element);
        String xPathElement = PropertiesFile.getPropValue(element);
        if (xPathElement == null) {
            xPathElement = element;
        }
        WebElement b = DriverManager.getDriver().findElement(By.xpath(xPathElement));
        String c = b.getAttribute("value");
        logger.info(c);
        return c;
    }
    public void scrollToTheBottomPage() {
        logger.info("scrollDownToElementWithJavaExecutor");
        JavascriptExecutor js = ((JavascriptExecutor) DriverManager.getDriver());
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}

