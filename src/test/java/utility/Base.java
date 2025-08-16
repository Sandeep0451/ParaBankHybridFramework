package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public Base(WebDriver driver) {
        Base.driver = driver;
        Base.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

}

