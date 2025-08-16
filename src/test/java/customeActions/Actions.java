package customeActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Base;

public class Actions extends Base {

    public Actions(WebDriver driver) {
        super(driver);
    }

    public void sendKeysOrValidate(String data, WebElement element){
        try {
            if(data.contains("validate")){
                System.out.println("⚠️");
            }else{
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.clear();
                element.sendKeys(data);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Warning: Could not clear or send keys to element: " + e.getMessage());
        }
    }

    public void click( WebElement element){
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
        }
        catch (Exception e){
            System.out.println("⚠️ Warning: Could not click element: " + e.getMessage());

        }
    }
}
