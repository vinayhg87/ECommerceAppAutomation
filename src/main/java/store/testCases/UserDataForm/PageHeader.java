package main.java.store.testCases.UserDataForm;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import main.java.store.PageWebelements.UserDataForm;
import main.java.store.caller.Commons;
import main.java.store.capabilities.AppiumCapabilities;
import main.java.store.capabilities.Screenshot;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PageHeader extends AppiumCapabilities implements Commons {

    public PageHeader(String SheetName, int Row) throws IOException, InterruptedException {

        System.out.println("Executing test case to validate Header : General Store.");
        AndroidDriver<AndroidElement> driver = capabilities();

        try {
            UserDataForm webele = new UserDataForm(driver);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            if (webele.pageHeader.getText().equals("General Store")) {
                webele.countrydropDown.click();
                driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));").click();
                webele.customerName.sendKeys("Vinay Gopinath");
                driver.hideKeyboard(); // this is to hide the keyboard layout in-case if it is blocking the other web-elements.
                webele.maleRadiobutton.click();
                new Screenshot(driver, "PageHeader");
                webele.clickShopbutton.click();
                excel.ReadWrite(SheetName, Row, 3, "PASS");
                System.out.println("Test case Passed.");
            } else {
                System.out.println("Test case Failed. Page header name did not match. The expected value is 'General Store'");
                excel.ReadWrite(SheetName, Row, 3, "FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test case Failed.");
            excel.ReadWrite(SheetName, Row, 3, "FAIL");
        } finally {
            Thread.sleep(1000);
            driver.closeApp();
        }
    }
}