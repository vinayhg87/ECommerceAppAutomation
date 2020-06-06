package main.java.store.capabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.IOException;
import java.net.URL;

public class AppiumCapabilities {

    public AndroidDriver<AndroidElement> capabilities() throws IOException {

        PropertiesReader prop = new PropertiesReader();
        AndroidDriver<AndroidElement> driver = null;

        /* Appium capabilities code for android - Emulator name is "Android_Automation_AVD" */
        DesiredCapabilities cap = new DesiredCapabilities();
        String device = prop.readProperties("device.type");
        if (device.equals("emulator_device")) {
            /* This is for Emulator */
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android_Automation_AVD");
        } else if (device.equalsIgnoreCase("real_device")) {
            /* This is a real Android device */
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        } else {
            System.out.println("Invalid value. Program Aborted.");
            System.exit(1);
        }

        /* Selecting browser option */
        String appType = prop.readProperties("app.type");
        if (appType.equalsIgnoreCase("chrome")) {
            String chromeDriver = prop.readProperties("chrome.driver.path");
            cap.setCapability("chromedriverExecutable", chromeDriver);
            cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        } else if (appType.equalsIgnoreCase("apk")) {
            /* Loading APK */
            String apkPath = prop.readProperties("apk.path");
            cap.setCapability(MobileCapabilityType.APP, apkPath);
        } else {
            System.out.println("Invalid Value. Program Aborted.");
            System.exit(1);
        }

        try {
            cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        } catch (NullPointerException e) {
            System.out.println("Driver initialized to NULL value. Program Aborted.");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Exception occurred" + e);
            System.exit(1);
        }

        return driver;
    }
}
