package runners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import regressions.Regression_001;
import utility.Base;

public class Runner extends Base {

    WebDriver driver;

    public Runner(WebDriver driver) {
        super(driver);
    }
    public Runner() {
        super();
    }


    @BeforeTest
    public void creationOfWebDriver(){
        Base.initExtentReport();
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
    }

    @Test
    public void test() throws InterruptedException {
        Base.createTest("Basic Parabank test");
        Regression_001 reg = new Regression_001(driver);
        reg.regressionMethod();
    }

    @AfterTest
    public void onFinish() {
        Base.flushReport();  // ✅ Write report to file
    }
}
