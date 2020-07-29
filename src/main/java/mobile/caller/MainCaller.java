package main.java.mobile.caller;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import main.java.mobile.capabilities.AppiumCapabilities;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

public class MainCaller implements Commons {

    public static void main(String[] args) throws IOException, InvalidFormatException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException,
            InterruptedException {

        /* Executing tests on mobile application  */
        if (prop.readProperties("app.type").equalsIgnoreCase("apk")) {
            System.out.println("Executing tests on mobile application.");

            /* Executing test cases for User Data Form */
            String SheetName = "UserDataForm";
            int RowCount = excel.RowCount(SheetName);
            for (int i = 1; i <= RowCount; i++) {
                if (!excel.ReadExcel(SheetName, i, 3).equalsIgnoreCase("PASS")
                    || excel.ReadExcel(SheetName, i, 3).equalsIgnoreCase(" ")) {
                    String ClassName = "main.java.mobile.testCases.app."
                            + excel.ReadExcel(SheetName, i, 2);
                    /* Class is a raw type. References to generic type Class<T> should be parameterized.
                       Hence using Class<?> to parameterize. if <?> not included, there wont be any error. compiler just throws warning.
                       Same reason for Constructor<?>. This is the better way of implementation */ 
                    Class<?> UserDataFormClass = Class.forName(ClassName);
                    Constructor<?> constructor = UserDataFormClass.getConstructor(String.class, int.class);
                    constructor.newInstance(SheetName, i);
                } else {
                    System.out.println("This test case " + excel.ReadExcel(SheetName, i, 2)
                                                                         + " was executed with status PASS.");
                }
            }
        }

        /* Executing tests on mobile browser like chrome  */
        else if (prop.readProperties("app.type").equalsIgnoreCase("browser")) {
            System.out.println("Executing tests on mobile browser.");
            AppiumCapabilities cap = new AppiumCapabilities();
            AndroidDriver<AndroidElement> driver = cap.capabilities();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.stanford.edu/");
            Thread.sleep(2000);
            //driver.findElementById("search-toggle").click();
            driver.findElement(By.id("search-toggle")).click();
            driver.findElementById("search-field").sendKeys("dfsdf");

        } else {
            System.out.println("Invalid option. Check the value of app.type in property file androidDevice.properties.");
            System.out.println("app.type can accept 2 values i.e 'chrome' or 'apk'");
            System.exit(1);
        }
    }
}
