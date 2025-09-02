package customeActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utility.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class Actions extends Base {

    private static final Logger logger = LogManager.getLogger(Actions.class);
    public Actions(WebDriver driver) {
        super(driver);
    }

    public void allActionsMethod(String data, String label,String xpath) {

        //Map<String, String> parsed = parseCellValue(data);

        String dat = label.substring(0, 3).toUpperCase();
        Object retData = null;

        WebElement element = getElement(driver, xpath);

        switch (dat) {
            case "TXT":
                sendKeysOrValidate(data, element);
                break;
            case "BTN" :
            case "LNK" :
                clickData(data, element);
                break;
            case "SEL" :
                selectData(data, element);
                break;
            /*case "VERE" :
                retData=  verifyElement(element);
                break;
            case "VERT" :
                retData =  getElementText(element);
                break;
            */
        }
    }

    public Map<String, String> parseCellValue(String cellValue) {
        Map<String, String> result = new HashMap<>();

        // Split by newline (each line has key:value format)
        String[] lines = cellValue.split("\\r?\\n");

        for (String line : lines) {
            String[] parts = line.split(":", 2); // Split only into 2 parts
            if (parts.length == 2) {
                String key = parts[0].trim().toLowerCase();   // e.g., "data", "xpath"
                String value = parts[1].trim();               // e.g., "test data", "//*[@class='test']"
                result.put(key, value);

            }
        }
        return result;
    }

    public void sendKeysOrValidate(String data, WebElement element){
        try {
            if(data.contains("validate")){
                String[] str = data.split("=");
                if(element.getAttribute("value").equals(str[0])){
                    logger.info("Expected data : "+ str[0]+" and actual data : " + element.getAttribute("value") +" are equal");
                    test.pass("Expected data : "+ str[0]+" and actual data : " + element.getAttribute("value") +" are equal");
                }else{
                    logger.warn("⚠️ Warning: Expected data : "+ str[0]+" and actual data : " + element.getAttribute("value") +" are not equal");
                    test.warning("⚠️ Warning: Expected data : "+ str[0]+" and actual data : " + element.getAttribute("value") +" are not equal");
                }
            }else if(!data.equals("N/A")){
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.clear();
                element.sendKeys(data);
                logger.info("Sendkeys to element: " + element.toString());
                test.pass("Sendkeys to element: " + element.toString());

            }
        } catch (Exception e) {
            logger.warn("⚠️ Warning: Could not clear or send keys to element: " + e.getMessage());
            test.warning("⚠️ Warning: Could not clear or send keys to element: " + e.getMessage());
        }
    }

    public void click( WebElement element){
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Clicked on element: " + element.toString());
            test.pass("Clicked on element: " + element.toString());
        }
        catch (Exception e){
            logger.warn("Could not click element: " + e.getMessage());
            test.warning("Could not click element: " + e.getMessage());

        }
    }
    public void clickData(String data,  WebElement element){
        try {
            if(data.contains("Click")) {
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
                logger.info("Clicked on element: " + element.toString());
                test.pass("Clicked on element: " + element.toString());
            }
        }
        catch (Exception e){
            logger.warn("Could not click element: " + e.getMessage());
            test.warning("Could not click element: " + e.getMessage());

        }
    }

    public void selectData(String data, WebElement element){
        try {
            if(data.contains("validate")){
                String[] tdata= data.split("=");
                String[] mainData = tdata[1].split("\\|");
                String eledata = element.getText();
                if(mainData[0].equals(eledata)){
                    logger.info("Actual " +eledata+" and Expected "+ mainData[0]+" data is matched");
                    test.pass("Actual " +eledata+" and Expected "+ mainData[0]+" data is matched");
                }else {
                    logger.warn("Actual " +eledata+" and Expected "+ mainData[0]+" data is not matched");
                    test.warning("Actual " +eledata+" and Expected "+ mainData[0]+" data is not matched");
                }
                
                System.out.println("⚠️");
            }else if(!data.equals("N/A")){
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));

                Select dropdown = new Select(element);
                dropdown.selectByVisibleText(data);
                logger.info("Selected value :"+data+ " from dropdown element :"+element.toString());
                test.pass("Selected value :"+data+ " from dropdown element :"+element.toString());
            }
        } catch (Exception e) {
            logger.warn("⚠️ Warning: Could not select data at element: " + e.getMessage());
            test.warning("⚠️ Warning: Could not select data at element: " + e.getMessage());
        }
    }

    public boolean verifyElement(WebElement element){
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element :"+element.toString()+"is present at desired location");
            test.pass("Element :"+element.toString()+"is present at desired location");
            return true;
        }
        catch (Exception e){
            logger.warn("⚠️ Warning: element is not present on UI " + e.getMessage());
            test.warning("⚠️ Warning: element is not present on UI " + e.getMessage());

        }
        return false;
    }

    public String getElementText(WebElement element){
        String text = "";
        try {
           
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));

            text =  element.getText();
            logger.info("Get text of element :"+element.toString()+"is success");
            test.pass("Get text of element :"+element.toString()+"is success");
        }
        catch (Exception e){
            logger.warn("⚠️ Warning: element is not present on UI " + e.getMessage());
            test.warning("⚠️ Warning: element is not present on UI " + e.getMessage());

        }
        return text;
    }

    public  WebElement getElement(WebDriver driver, String xpathExpression) {
        return driver.findElement(By.xpath(xpathExpression));
    }


}
