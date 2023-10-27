package org.example.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class iOSActions extends AppiumUtils {
    public iOSActions(IOSDriver driver) {
        super(driver);
        this.driver = driver;

    }
    //Currently using a windows device so iOS automation is not possible
    // @iOSXCUITFindBy(attribute = "value") to replace the @AndroidFindBy(attribute = "value")
    //IOSDriver to replace Android Driver
}
