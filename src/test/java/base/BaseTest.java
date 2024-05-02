package base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    private static AppiumDriverLocalService server;
    protected static ThreadLocal <AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

    public static AppiumDriverLocalService getServer() {
        return server;
    }
    public static void setServer(AppiumDriverLocalService server) {
        BaseTest.server = server;
    }

    public AppiumDriver getDriver() {
        return driver.get();
    }
    public static void setDriver(AppiumDriver driver2) {
        //BaseTest.driver = driver;
        driver.set(driver2);
    }

    @BeforeSuite
    public void beforeSuite() throws Exception, Exception {
        ThreadContext.put("ROUTINGKEY", "ServerLogs");
//		server = getAppiumService(); // -> If using Mac, uncomment this statement and comment below statement
        server = getAppiumServerDefault(); // -> If using Windows, uncomment this statement and comment above statement
//        if(!checkIfAppiumServerIsRunnning(4723)) {
            server.start();
            server.clearOutPutStreams(); // -> Comment this if you want to see server logs in the console
 //           utils.log().info("Appium server started");
 //       } else {
 //           utils.log().info("Appium server already running");
 //       }
    }
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if(server.isRunning()){
            server.stop();
//            utils.log().info("Appium server stopped");
        }
    }
    @BeforeTest
    public void beforeTest() throws MalformedURLException {
        AppiumDriver driver;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "SM G950U1");
        desiredCapabilities.setCapability("udid", "988e90334956495136");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.swaglabsmobileapp");
        desiredCapabilities.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
        URL url = new URL("http://127.0.0.1:4723");

        String appPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "app" + File.separator + "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
        desiredCapabilities.setCapability("app", appPath);
        driver = new AndroidDriver(url, desiredCapabilities);
        setDriver(driver);
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }
    public void waitForVisibility(WebElement e) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(e));
    }
    public void clear(WebElement e) {
        waitForVisibility(e);
        e.clear();
    }

    public void click(WebElement e) {
        waitForVisibility(e);
        e.click();
    }
    public void click(WebElement e, String msg) {
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(WebElement e, String txt) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }
    public void sendKeys(WebElement e, String txt, String msg) {
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(WebElement e){//, String msg) {
        String txt = null;
        txt = getAttribute(e, "text");
        return txt;
    }

    public void closeApp() {
        ((InteractsWithApps) getDriver()).terminateApp("com.swaglabsmobileapp");
    }

    public void launchApp() {
        ((InteractsWithApps) getDriver()).activateApp("com.swaglabsmobileapp");
    }

    public WebElement scrollToElement(String childLocAttr, String childLocValue) {
        return getDriver().findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" + ".scrollable(true)).scrollIntoView("
                        + "new UiSelector()."+ childLocAttr +"(\"" + childLocValue + "\"));"));
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if(getDriver() != null){
            getDriver().quit();
        }
    }
}
