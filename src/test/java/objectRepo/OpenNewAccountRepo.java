package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenNewAccountRepo {

    WebDriver driver;

    public OpenNewAccountRepo(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Open New Account')]")
    public WebElement lnkOpenNewAccount;

    @FindBy(xpath = "//select[@class='input']")
    public WebElement selTypeOfAccount;

    @FindBy(xpath = "//select[@id='fromAccountId']")
    public WebElement selAccountID;

    @FindBy(xpath = "//input[@value='Open New Account']")
    public WebElement btnOpenNewAccount;

    @FindBy(xpath = "//p[contains(text(),'Congratulations, your account is now open.')]")
    public WebElement lblAccountOpenSuccessMsg;

    @FindBy(xpath = "//a[@id='newAccountId']")
    public WebElement lblNewAccountID;

}
