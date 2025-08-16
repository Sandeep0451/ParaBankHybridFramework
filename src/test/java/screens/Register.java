package screens;

import customeActions.Actions;
import objectRepo.RegisterRepo;
import org.openqa.selenium.WebDriver;
import customeActions.Actions;
import utility.GetData;

import java.util.List;
import java.util.Map;

public class Register {
    private Actions actions;
    RegisterRepo register;
    String screenName = "Register";
    Map<String, String> rowData;


    public Register(WebDriver driver) {
        this.register = new RegisterRepo(driver);
        actions = new Actions(driver);
    }



    public void registerScreen(String testCaseName, String transaction){
        List<Map<String, String>> testData = GetData.getDataForTestCase(screenName, testCaseName,transaction);
        int iteration=0;
        while(iteration<testData.size()){
            rowData  = testData.get(iteration);


            actions.click(register.lnkRegister);
            actions.sendKeysOrValidate(rowData.get("TXT_FirstName"), register.txtFirstName);
            actions.sendKeysOrValidate(rowData.get("TXT_LastName"), register.txtLastName);
            actions.sendKeysOrValidate(rowData.get("TXT_Address"), register.txtAddress);
            actions.sendKeysOrValidate(rowData.get("TXT_City"), register.txtCity);
            actions.sendKeysOrValidate(rowData.get("TXT_Street"), register.txtState);
            actions.sendKeysOrValidate(rowData.get("TXT_ZipCode"), register.txtZip);
            actions.sendKeysOrValidate(rowData.get("TXT_PhoneNumber"), register.txtPhoneNumber);
            actions.sendKeysOrValidate(rowData.get("TXT_SSN"), register.txtSSN);
            actions.sendKeysOrValidate(rowData.get("TXT_Username"), register.txtUserName);
            actions.sendKeysOrValidate(rowData.get("TXT_Password"), register.txtPassword);
            actions.sendKeysOrValidate(rowData.get("TXT_Confirm"), register.txtConfirmPassword);
            actions.click(register.btnRegister);

            iteration++;
        }
        }
    }
