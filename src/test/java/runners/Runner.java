package runners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import regressions.Regression_001;
import regressions.Regression_002;
import regressions.Regression_003;
import utility.Base;

public class Runner extends Base {

    WebDriver driver;

    public Runner(WebDriver driver) {
        super(driver);
    }
    public Runner() {
        super();
    }


    @BeforeMethod
    public void creationOfWebDriver(){
        Base.initExtentReport();
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
    }

    @Test(priority = 1,enabled = false)
    public void testOne() throws InterruptedException {
        Base.createTest("Basic Parabank test");
        Regression_001 reg = new Regression_001(driver);
        reg.regressionMethod();
    }

    @Test(priority = 2,enabled = false)
    public void testTwo() throws InterruptedException {
        Base.createTest("Random Parabank test");
        Regression_002 reg = new Regression_002(driver);
        reg.regressionMethod();
    }

    @Test(priority = 3,enabled = true)
    public void testThree() throws InterruptedException {
        Base.createTest("Basic Parabank test");
        Regression_003 reg = new Regression_003(driver);
        reg.regressionMethod();
    }

    @AfterMethod
    public void close(){
        Base.driver.quit();
    }

    @AfterTest
    public void onFinish() {
        Base.flushReport();
    }
}
