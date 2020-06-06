package main.java.store.caller;

import main.java.store.ExcelOps.ExcelOperations;
import main.java.store.capabilities.PropertiesReader;

import java.io.File;

public interface Commons {

    String currentDir = System.getProperty("user.dir");
    String ScreenshotFilePath = currentDir + File.separator + "AppScreenshots" + File.separator;
    String ExcelFilePath = currentDir + File.separator + "ExcelFiles" + File.separator + "ExcelInput.xlsx";
    ExcelOperations excel = new ExcelOperations();
    PropertiesReader prop = new PropertiesReader();
}
