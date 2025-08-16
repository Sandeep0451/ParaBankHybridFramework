package screens;

import customeActions.Actions;
import objectRepo.AccountDetailsRepo;
import objectRepo.OpenNewAccountRepo;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utility.GetData;

import java.util.List;
import java.util.Map;

public class AccountDetails {
    private Actions actions;
    AccountDetailsRepo accountDetails;
    String screenName = "AccountDetails";
    Map<String, String> rowData;


    public AccountDetails(WebDriver driver) {
        this.accountDetails = new AccountDetailsRepo(driver);
        actions = new Actions(driver);
    }

    public void accountDetailsScreen(String testCaseName, String transaction) throws InterruptedException {
        List<Map<String, String>> testData = GetData.getDataForTestCase(screenName, testCaseName,transaction);
        int iteration=0;
        while(iteration<testData.size()){
            rowData  = testData.get(iteration);

            Assert.assertTrue(actions.verifyElement(accountDetails.lblAccountDetails), "Account Details screen displayed");

            String accountIDEXP = actions.getElementText(accountDetails.lblAccountID);
            if(accountIDEXP.equals(OpenNewAccount.accountID)){
                System.out.println("Account ID displayed correct");
            }

            actions.sendKeysOrValidate(rowData.get("TXT_AccountType"), accountDetails.lblAccountTYpe);
            actions.selectData(rowData.get("SEL_ActivityPeriod"), accountDetails.selActivityPeriod);
            actions.selectData(rowData.get("SEL_TransactionType"), accountDetails.selTransactionType);
            Thread.sleep(2000);
            actions.click(accountDetails.btnGo);



            iteration++;
        }
    }
}
