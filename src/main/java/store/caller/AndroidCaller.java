package main.java.store.caller;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class AndroidCaller implements Commons {

    public static void main(String[] args) throws IOException, InvalidFormatException, ClassNotFoundException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        /* Executing tests on mobile application  */
        if (prop.readProperties("app.type").equalsIgnoreCase("apk")) {
            System.out.println("Executing tests on mobile application.");

            /* Executing test cases for User Data Form */
            String SheetName = "UserDataForm";
            int RowCount = excel.RowCount(SheetName);
            for (int i = 1; i <= RowCount; i++) {
                if (excel.ReadExcel(SheetName, i, 3).equalsIgnoreCase("FAIL")
                        || excel.ReadExcel(SheetName, i, 3).equalsIgnoreCase("NO RUN")
                        || excel.ReadExcel(SheetName, i, 3).equalsIgnoreCase(" ")) {
                    String ClassName = "main.java.store.testCases.UserDataForm."
                            + excel.ReadExcel(SheetName, i, 2);
                    Class UserDataFormClass = Class.forName(ClassName);
                    Constructor constructor = UserDataFormClass.getConstructor(String.class, int.class);
                    Object obj = constructor.newInstance(SheetName, i);
                } else {
                    System.out.println("This test case " + excel.ReadExcel(SheetName, i, 2)
                                                                         + " was executed with status PASS.");
                }
            }
        }

        /* Executing tests on mobile browser like chrome  */
        else if (prop.readProperties("app.type").equalsIgnoreCase("chrome")) {
            System.out.println("Executing tests on mobile browser.");

        } else {
            System.out.println("Invalid option. Check the value of app.type in property file androidDevice.properties.");
            System.out.println("app.type can accept 2 values i.e 'chrome' or 'apk'");
            System.exit(1);
        }
    }
}
