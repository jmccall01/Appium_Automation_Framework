package org.generalstore;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.generalstore.pageObjects.Android.FormPage;
import org.generalstore.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

import static java.time.Duration.*;

public class Basis extends AppiumUtils {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;
    @BeforeClass
    public void ConfigureAppium() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setChromedriverExecutable("C:\\Users\\joshm\\Documents\\QA\\chromedriver.exe");
        options.setDeviceName("Test Device");
        options.setApp("C:\\Users\\joshm\\IdeaProjects\\automation_framework\\src\\resources\\General-Store.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        formPage = new FormPage(driver);
    }

    public Double getFormattedAmount(String amount){
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        //service.stop();
    }
}