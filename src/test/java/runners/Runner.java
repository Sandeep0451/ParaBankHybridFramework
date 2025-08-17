package runners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import regressions.Regression_001;
import utility.ExtentManager;

public class Runner {

    WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeTest
    public void creationOfWebDriver(){
        test = extent.createTest("ParaBank Basic Scenario");
        extent = ExtentManager.getInstance();
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
    }

    @Test
    public void test() throws InterruptedException {
        Regression_001 reg = new Regression_001(driver);
        reg.regressionMethod();
    }
}
