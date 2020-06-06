package main.java.store.capabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public String readProperties(String property) throws IOException {

        /* Getting device and browser details from properties file */
        String currentDirectory = System.getProperty("user.dir");
        String androidDevices_prop = currentDirectory + File.separator + "androidDevice.properties";
        Properties prop = new Properties();
        prop.load(new FileInputStream(androidDevices_prop));

        return prop.getProperty(property);
    }
}
