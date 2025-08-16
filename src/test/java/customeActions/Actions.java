package customeActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
        catch (Exception e){
            System.out.println("⚠️ Warning: Could not click element: " + e.getMessage());

        }
    }

    public void selectData(String data, WebElement element){
        try {
            if(data.contains("validate")){
                String[] tdata= data.split("=");
                String[] mainData = tdata[1].split("\\|");
                String eledata = element.getText();
                if(mainData[0].equals(eledata)){
                    System.out.println("Actual " +eledata+" and Expected "+ mainData[0]+" data is matched");
                }else {
                    System.out.println("Actual " +eledata+" and Expected "+ mainData[0]+" data is not matched");
                }
                
                System.out.println("⚠️");
            }else if(!data.equals("N/A")){
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));

                Select dropdown = new Select(element);
                dropdown.selectByVisibleText(data);

            }
        } catch (Exception e) {
            System.out.println("⚠️ Warning: Could not clear or send keys to element: " + e.getMessage());
        }
    }

    public boolean verifyElement(WebElement element){
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }
        catch (Exception e){
            System.out.println("⚠️ Warning: element is not present on UI " + e.getMessage());

        }
        return false;
    }

    public String getElementText(WebElement element){
        String text = "";
        try {
           
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            text =  element.getText();
        }
        catch (Exception e){
            System.out.println("⚠️ Warning: element is not present on UI " + e.getMessage());

        }
        return text;
    }
}
