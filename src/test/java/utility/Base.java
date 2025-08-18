package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Base {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    public static String filePath = System.getProperty("user.dir") + "/Reports/";

    public Base(WebDriver driver) {
        Base.driver = driver;
        Base.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public Base() {
        // empty constructor for TestNG
    }

    public static void initExtentReport() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter(filePath+"/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }


}

