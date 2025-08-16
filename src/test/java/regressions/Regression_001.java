package regressions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import screens.Register;


public class Regression_001 {

    String transaction;
    Register register;

    public Regression_001(WebDriver driver) {
        this.register = new Register(driver);  // ✅ initialize here
    }

    public void regressionMethod(){
        transaction = "New";
        register.registerScreen("TC_01",transaction);

    }
}
