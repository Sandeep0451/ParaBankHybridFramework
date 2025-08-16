package runners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import regressions.Regression_001;

public class Runners {

    WebDriver driver;


    @BeforeTest
    public void creationOfWebDriver(){
        driver = new ChromeDriver();
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize();
    }

    @Test
    public void test(){
        Regression_001 reg = new Regression_001(driver);
        reg.regressionMethod();
    }
}
