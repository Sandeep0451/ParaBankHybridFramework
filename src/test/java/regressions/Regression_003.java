package regressions;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import screens.AccountDetails;
import screens.OpenNewAccount;
import screens.Register;
import screens.RegisterTwo;


public class Regression_003 {

    String testCaseName;
    String transaction;
    RegisterTwo register;
    OpenNewAccount openNewAccount;
    AccountDetails accountDetails;

    public Regression_003(WebDriver driver) {
        this.register = new RegisterTwo(driver);
        this.openNewAccount = new OpenNewAccount(driver);
        this.accountDetails = new AccountDetails(driver);
    }

    public void regressionMethod() throws InterruptedException {

        register.registerScreen();
        openNewAccount.OpenNewAccountScreen(testCaseName,transaction);
        accountDetails.accountDetailsScreen(testCaseName,transaction);

    }
}
