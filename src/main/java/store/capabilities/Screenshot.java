package main.java.store.capabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import main.java.store.caller.Commons;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class Screenshot implements Commons {

    public Screenshot(AndroidDriver<AndroidElement> driver, String ScreenshotName) {

        try {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(ScreenshotFilePath + ScreenshotName + ".jpg"));
        } catch (Exception e) {
            System.out.println("Error occurred while taking screenshot : " + e);
        }
    }
}
