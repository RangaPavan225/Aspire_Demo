package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {

    static WebDriver driver;

    public static WebDriverWait wait;

    public static WebDriver getDriver() {

        System.out.println(System.getProperty("user.dir") + "//testResources//Chrome//chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//testResources//Chrome//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        return driver;
    }

    public void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
