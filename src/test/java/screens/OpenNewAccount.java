package screens;

import customeActions.Actions;
import objectRepo.OpenNewAccountRepo;
import objectRepo.RegisterRepo;
import org.openqa.selenium.WebDriver;
import utility.GetData;

import java.util.List;
import java.util.Map;

public class OpenNewAccount {
    private Actions actions;
    OpenNewAccountRepo newAccountRepo;
    String screenName = "OpenNewAccount";
    Map<String, String> rowData;
    public String accountID;

    public OpenNewAccount(WebDriver driver) {
        this.newAccountRepo = new OpenNewAccountRepo(driver);
        actions = new Actions(driver);
    }

    public void OpenNewAccountScreen(String testCaseName, String transaction) throws InterruptedException {
        List<Map<String, String>> testData = GetData.getDataForTestCase(screenName, testCaseName,transaction);
        int iteration=0;
        while(iteration<testData.size()){
            rowData  = testData.get(iteration);


            actions.click(newAccountRepo.lnkOpenNewAccount);
            actions.selectData(rowData.get("SEL_AccountType"), newAccountRepo.selTypeOfAccount);
            actions.selectData(rowData.get("SEL_AccountID"), newAccountRepo.selAccountID);
            Thread.sleep(5000);
            actions.click(newAccountRepo.btnOpenNewAccount);

            if(actions.verifyElement(newAccountRepo.lblAccountOpenSuccessMsg)){
                System.out.println("Account creation successfully");
            }else {
                System.out.println("Account creation Unsuccessfully");
            }
            accountID = actions.getElementText(newAccountRepo.lblNewAccountID);
            actions.click(newAccountRepo.lblNewAccountID);

            iteration++;
        }
    }
}
