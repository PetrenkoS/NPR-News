package NPR_News;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class TestHelper {
    protected AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.npr.android.news");
        capabilities.setCapability("appActivity", ".NewsListActivity");
        capabilities.setCapability("app", "C:/Users/Admin/JavaAppiumAutomation/apks/npr.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() {
        driver.quit();

    }

    protected void checkProgramsOnHeader() {
        WebElement topic_item = waitForElementPresent(By.id("android:id/text1"),
                "Cannot find topic item",
                30);

        String article_title = topic_item.getAttribute("text");
        Assert.assertEquals("We see unexpected title!",
                "programs",
                article_title);
    }

    protected void chooseProgramsOnHeader() {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'programs')]"),
                "Cannot find programs",
                15);
    }

    protected void pressNewsHeaderButton() {
        waitForElementAndClick(By.id("org.npr.android.news:id/spinner"),
                "Cannot find news on the header",
                15);
    }

    protected void ignoreAlerts() {
        waitForElementAndClick(By.id("org.npr.android.news:id/not_now"),
                "Cannot find such element",
                15);
    }

    protected void checkSearchResultsPage() {
        waitForElementNotPresent(By.xpath("//*[@class='android.widget.TextView'][text()='Start date']"),
                "No element Start date",
                15);
    }

    protected void pressMagnifierButton() {
        waitForElementAndClick(By.id("org.npr.android.news:id/search_go_button"),
                "Cannot find magnifier button",
                15);
    }

    protected void typeInSearchField() {
        waitForElementAndSendKeys(By.xpath("//*[@resource-id='org.npr.android.news:id/SearchText']"),
                "Trump",
                "Cannot find search field",
                5);
    }

    protected void goToSearchPage() {
        waitForElementAndClick(By.xpath("//*[@resource-id='org.npr.android.news:id/action_search']"),
                "Cannot find search field",
                5);
    }

    public void bottomTopSwipe(int timeduration) {


        Dimension size = driver.manage().window().getSize();
        int height = size.getHeight();
        int width = size.getWidth();
        int x = width / 2;
        int starty = (int) (size.height * 0.50);
        int endy = (int) (size.height * 0.20);
        driver.swipe(x, starty, x, endy, timeduration);

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));

    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


}
