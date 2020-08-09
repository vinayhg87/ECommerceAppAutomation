package main.java.mobile.caller;

import java.io.File;
import main.java.mobile.ExcelOps.ExcelOperations;
import main.java.mobile.capabilities.PropertiesReader;

public interface Commons {

    String currentDir = System.getProperty("user.dir");
    String ScreenshotFilePath = currentDir + File.separator + "AppScreenshots" + File.separator;
    String ExcelFilePath = currentDir + File.separator + "ExcelFiles" + File.separator + "ExcelInput.xlsx";
    ExcelOperations excel = new ExcelOperations();
    PropertiesReader prop = new PropertiesReader();
}
