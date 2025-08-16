package regressions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import screens.OpenNewAccount;
import screens.Register;


public class Regression_001 {

    String testCaseName;
    String transaction;
    Register register;
    OpenNewAccount openNewAccount;

    public Regression_001(WebDriver driver) {
        this.register = new Register(driver);
        this.openNewAccount = new OpenNewAccount(driver);// ✅ initialize here
    }

    public void regressionMethod() throws InterruptedException {
        testCaseName = "TC_01";
        transaction = "New";
        register.registerScreen(testCaseName,transaction);
        openNewAccount.OpenNewAccountScreen(testCaseName,transaction);

    }
}
