package org.generalstore.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.generalstore.pageObjects.Android.FormPage;
import org.generalstore.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.time.Duration.*;

public class Basis extends AppiumUtils {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;
    @BeforeClass
    public void ConfigureAppium() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\joshm\\IdeaProjects\\automation_framework\\src\\main\\java\\resources\\data.properties");
        prop.load(fis);
        String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");
        service = startAppiumServer(ipAddress, Integer.parseInt(port));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setChromedriverExecutable("C:\\Users\\joshm\\Documents\\QA\\chromedriver.exe");
        options.setDeviceName("Test Device");
        options.setApp("C:\\Users\\joshm\\IdeaProjects\\automation_framework\\src\\resources\\General-Store.apk");
        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
        formPage = new FormPage(driver);
    }

    @BeforeClass
    public ExtentReports extentReport(){
        String path = System.getProperty("user.dir") + "\\ExtentReports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("E2E Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Josh McCall");
        return extent;
    }

    public Double getFormattedAmount(String amount){
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        service.stop();
    }
}