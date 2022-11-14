package helpers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.Configloader;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileHelper extends Configloader {
    public static AndroidDriver androidDriver;

    public static void connectLocalAndriodDevice() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "avd_one");
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        caps.setCapability("chromedriverExecutable", "/Users/ravis/Documents/Ravi Kumar Sharma QA/Successive.Tech/MarketCube Project/New API Automation Repo/mobile.automation.demo/src/test/resources/drivers/chromedriver.exe");
        //caps.setCapability(MobileCapabilityType.APP, getCustomAppPath("ApiDemos-debug.apk"));
        setAndroidDriverURLandCapabilities(caps);
        log.info("Test has started successfully.");
        log.info("Device Name: {}, Browser Name: {}, ", caps.getCapability(MobileCapabilityType.DEVICE_NAME).toString(), caps.getCapability(MobileCapabilityType.BROWSER_NAME).toString());

    }

    public static void connectYourAndriodDevice() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Your_Device_Name");
        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        caps.setCapability("chromedriverExecutable", "/Users/ravis/Documents/Ravi Kumar Sharma QA/Successive.Tech/MarketCube Project/New API Automation Repo/mobile.automation.demo/src/test/resources/drivers/chromedriver.exe");
        // caps.setCapability(MobileCapabilityType.UDID, "Insert UDID Here");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "Andriod Version");
        //caps.setCapability(MobileCapabilityType.APP, getCustomAppPath("ApiDemos-debug.apk"));
        setAndroidDriverURLandCapabilities(caps);
        log.info("Test has started successfully.");
        log.info("Device Name: {}, Browser Name: {}, ", caps.getCapability(MobileCapabilityType.DEVICE_NAME).toString(), caps.getCapability(MobileCapabilityType.BROWSER_NAME).toString());
    }

    private static AndroidDriver setAndroidDriverURLandCapabilities(DesiredCapabilities capabilities) {
        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            androidDriver = new AndroidDriver(url, capabilities);
            androidDriver.get("https://test-ui.marketcube.io/login");
            androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            return androidDriver;
        } catch (Exception e) {
            log.error("Exception has been occurred");
            e.printStackTrace();
        }
        return null;
    }

    public static void closeAndQuitSession() {
        androidDriver.close();
        androidDriver.quit();
        log.info("Session has been terminated successfully.");
    }
}


