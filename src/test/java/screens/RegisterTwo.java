package screens;

import customeActions.Actions;
import objectRepo.RegisterRepo;
import org.openqa.selenium.WebDriver;
import customeActions.Actions;
import utility.GetData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RegisterTwo {
    private Actions actions;
    RegisterRepo register;

    Map<String, String> rowData;
    String randomDigit;
    public String randomUserName;

    public RegisterTwo(WebDriver driver) {
        this.register = new RegisterRepo(driver);
        actions = new Actions(driver);
    }



    public void registerScreen(){

        List<String> screens = GetData.getRegressionIDs();
        int iterationMain=1;

        while(iterationMain<screens.size()){


            String[] testCaseData = screens.get(0).split("\\|");
            String testCaseName = testCaseData[0];
            if(testCaseData[1].equalsIgnoreCase("YES")){

                String[] testScreens= screens.get(iterationMain).split("\\|");
                String transaction = testScreens[0];
                String screenName = testScreens[1];
                List<Map<String, String>> testData = GetData.getDataForTestCase(screenName, testCaseName,transaction);
                List<String> xpaths = GetData.readRowData(screenName,1);
                System.out.println(xpaths.size());
                int iteration=0;
                while(iteration<testData.size()){
                    rowData  = testData.get(iteration);
                    int forXpaths = 2;
                    int counter = 0;
                    for (Map.Entry<String, String> entry : rowData.entrySet()) {
                        counter++;
                        if (counter <= 2) continue;
                        actions.allActionsMethod(entry.getValue(),entry.getKey(),xpaths.get(forXpaths));
                        forXpaths++;
                    }

                    iteration++;
                }
            }
            iterationMain++;
        }

    }
}
