package regressions;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import screens.AccountDetails;
import screens.OpenNewAccount;
import screens.Register;


public class Regression_001 {

    String testCaseName;
    String transaction;
    Register register;
    OpenNewAccount openNewAccount;
    AccountDetails accountDetails;

    public Regression_001(WebDriver driver) {
        this.register = new Register(driver);
        this.openNewAccount = new OpenNewAccount(driver);
        this.accountDetails = new AccountDetails(driver);
    }

    public void regressionMethod() throws InterruptedException {
        testCaseName = "TC_01";
        transaction = "New";
        register.registerScreen(testCaseName,transaction);
        openNewAccount.OpenNewAccountScreen(testCaseName,transaction);
        accountDetails.accountDetailsScreen(testCaseName,transaction);

    }
}
