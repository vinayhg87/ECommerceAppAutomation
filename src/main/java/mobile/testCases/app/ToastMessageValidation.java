package main.java.mobile.testCases.app;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import main.java.mobile.PageWebelements.app.UserDataForm;
import main.java.mobile.capabilities.AppiumCapabilities;
import main.java.mobile.capabilities.Screenshot;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ToastMessageValidation extends AppiumCapabilities {

    public ToastMessageValidation(String SheetName, int Row) throws IOException, InterruptedException {

        System.out.println("Executing test case to validate Toast Error Message.");
        AndroidDriver<AndroidElement> driver = capabilities();

        try {
            UserDataForm webele = new UserDataForm(driver);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webele.countrydropDown.click();
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Canada\"));").click();
            driver.hideKeyboard(); // this is to hide the keyboard layout in-case if it is blocking the other web-elements.
            webele.maleRadiobutton.click();
            new Screenshot(driver, "MaleCustomer");
            webele.clickShopbutton.click();
            String ToastMessage = webele.toastMessage.getAttribute("name");
            System.out.println("The actual message is " + ToastMessage);
            if (ToastMessage.equals("Please enter your name")) {
                excel.ReadWrite(SheetName, Row, 3, "PASS");
                System.out.println("Test case Passed.");
            } else {
                System.out.println("Test case Failed.");
                System.out.println("Toast Message is not correct");
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
