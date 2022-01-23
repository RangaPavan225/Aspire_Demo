package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BrowserUtils;

public class Aspire_E2E {

    static WebDriver driver;

    //Invoke chrome browser and navigate to Aspire application
    @BeforeTest
    void NavigateToAspire() {
        driver = BrowserUtils.getDriver();
        driver.get("https://aspireapp.odoo.com/");
    }

    //Perform E2E(End to End) operation and verify the result with status
    @Test
    void performE2E() {
        E2EActions e2eActions = new E2EActions(driver);
        e2eActions.login();
        e2eActions.createProductFromInventory();
        e2eActions.createManufacturingItem();
        e2eActions.verifyCreatedManufacturingItem();
    }

    //Closing the browser
    @AfterTest
    void closeBrowser() {
        driver.quit();
    }
}
