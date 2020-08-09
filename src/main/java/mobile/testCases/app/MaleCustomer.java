package main.java.mobile.testCases.app;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import main.java.mobile.PageWebelements.app.UserDataForm;
import main.java.mobile.capabilities.AppiumCapabilities;
import main.java.mobile.capabilities.Screenshot;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MaleCustomer extends AppiumCapabilities {

    public MaleCustomer(String SheetName, int Row) throws IOException, InterruptedException {

        System.out.println("Executing test case for the UserDataForm Screen : Male Customer.");
        AndroidDriver<AndroidElement> driver = capabilities();
        try {
            UserDataForm webele = new UserDataForm(driver);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webele.countrydropDown.click();
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Denmark\"));").click();
            webele.customerName.sendKeys("Vinay Gopinath");
            driver.hideKeyboard(); // this is to hide the keyboard layout in-case if it is blocking the other web-elements.
            webele.maleRadiobutton.click();
            new Screenshot(driver, "MaleCustomer");
            webele.clickShopbutton.click();
            excel.ReadWrite(SheetName, Row, 3, "PASS");
            System.out.println("Test case Passed.");
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