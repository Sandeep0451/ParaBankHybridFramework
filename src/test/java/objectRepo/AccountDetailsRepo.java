package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountDetailsRepo {

    WebDriver driver;

    public AccountDetailsRepo(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(text(),'Account Details')]")
    public WebElement lblAccountDetails;

    @FindBy(xpath = "//td[contains(@id,'accountId')]")
    public WebElement lblAccountID;

    @FindBy(xpath = "//td[contains(@id,'accountType')]")
    public WebElement lblAccountTYpe;

    @FindBy(xpath = "//select[contains(@id,'month')]")
    public WebElement selActivityPeriod;

    @FindBy(xpath = "//select[contains(@id,'transactionType')]")
    public WebElement selTransactionType;

    @FindBy(xpath = "//input[contains(@value,'Go')]")
    public WebElement btnGo;
}
