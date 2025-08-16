package objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterRepo {

    WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Register')]")
    public WebElement lnkRegister;

    @FindBy(xpath = "//input[@name='customer.firstName']")
    public WebElement txtFirstName;

    @FindBy(xpath = "//input[@name='customer.lastName']")
    public WebElement txtLastName;

    @FindBy(xpath = "//input[@name='customer.address.street']")
    public WebElement txtAddress;

    @FindBy(xpath = "//input[@name='customer.address.city']")
    public WebElement txtCity;

    @FindBy(xpath = "//input[@name='customer.address.state']")
    public WebElement txtState;

    @FindBy(xpath = "//input[@name='customer.address.zipCode']")
    public WebElement txtZip;

    @FindBy(xpath = "//input[@name='customer.phoneNumber']")
    public WebElement txtPhoneNumber;

    @FindBy(xpath = "//input[@name='customer.ssn']")
    public WebElement txtSSN;

    @FindBy(xpath = "//input[@name='customer.username']")
    public WebElement txtUserName;

    @FindBy(xpath = "//input[@name='customer.password']")
    public WebElement txtPassword;

    @FindBy(xpath = "//input[@name='repeatedPassword']")
    public WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@value='Register']")
    public WebElement btnRegister;

    public RegisterRepo(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
