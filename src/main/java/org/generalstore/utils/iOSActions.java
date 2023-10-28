package org.generalstore.utils;

import io.appium.java_client.ios.IOSDriver;

public class iOSActions extends AppiumUtils {
    private final IOSDriver driver;

    public iOSActions(IOSDriver driver) {
        this.driver = driver;

    }
    //Currently using a windows device so iOS automation is not possible
    // @iOSXCUITFindBy(attribute = "value") to replace the @AndroidFindBy(attribute = "value")
    //IOSDriver to replace Android Driver
}
