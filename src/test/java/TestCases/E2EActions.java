package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.BrowserUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class E2EActions {

    WebDriver driver;

    BrowserUtils browserUtils = new BrowserUtils();

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    WebElement loginButton;

    @FindBy(xpath = "//div[text()='Inventory']/parent::a")
    WebElement inventoryOption;

    @FindBy(xpath = "//div[text()='Manufacturing']/parent::a")
    WebElement manufacturingOption;

    @FindBy(xpath = "//li[@class='breadcrumb-item active' and text()='Inventory Overview']")
    WebElement inventoryActiveHeader;

    @FindBy(xpath = "//li[@class='breadcrumb-item active' and text()='Products']")
    WebElement productsActiveHeader;

    @FindBy(xpath = "//button[contains(text(),'Create')]")
    WebElement createButton;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveButton;

    @FindBy(xpath = "//button[contains(text(),'Edit')]")
    WebElement editButton;

    @FindBy(xpath = "//label[text()='Product Name']/following-sibling::*/input")
    WebElement productNameField;

    @FindBy(xpath = "//span[text()='Update Quantity']")
    WebElement updateQuantityButton;

    @FindBy(xpath = "//div[@name='location_id']")
    WebElement locationDropdown;

    @FindBy(xpath = "//li/a[text()='WH/Stock']")
    WebElement whStockLocationOption;

    @FindBy(xpath = "//input[@name='inventory_quantity']")
    WebElement inventoryQuantityField;

    @FindBy(xpath = "//a[@title='Applications']")
    WebElement applicationsButton;

    @FindBy(xpath = "//div[@name='product_id']//input")
    WebElement productForMF;

    @FindBy(xpath = "//div[@name='bom_id']//input")
    WebElement billMaterial;

    @FindBy(xpath = "//*[text()='Create: Bill of Material']/parent::header/following-sibling::main//input[@name='product_qty']")
    WebElement bomProductQuantity;

    @FindBy(xpath = "//a[text()='Add a line']")
    WebElement addLineToConsume;

    @FindBy(xpath = "//tr[@class='o_data_row o_selected_row']//div[@name='product_id']//input")
    WebElement consumerTableProductId;

    @FindBy(xpath = "//input[@name='product_uom_qty']")
    WebElement consumeQuantityMOF;

    @FindBy(xpath = "//span[text()='Confirm']")
    WebElement confirmButton;

    @FindBy(xpath = "//span[text()='Create']")
    WebElement BOMCreate;

    @FindBy(xpath = "//input[@name='qty_producing']")
    WebElement MOFQuantity;

    @FindBy(xpath = "//span[text()='Mark as Done']")
    WebElement markAsDoneButton;

    @FindBy(xpath = "//button[@name='button_scrap']")
    WebElement scrapButton;

    @FindBy(xpath = "//span[@placeholder='Manufacturing Reference']")
    WebElement createdMFItem;

    @FindBy(xpath = "//span[@name='qty_producing']")
    WebElement MFProvidedQuantity;

    @FindBy(xpath = "//span[@name='product_uom_qty']")
    WebElement MFActualQuantity;

    @FindBy(xpath = "//span[text()='Save']")
    WebElement BOFSaveButton;

    @FindBy(xpath = "//div[contains(@class,'o_Message_trackingValueNewValue') and text()='Done']")
    WebElement doneStatus;

    public String menu = "//a[contains(text(),'%s')]";

    public String subMenu = "//a[@role='menuitem']/span[text()='%s']";

    public String dropdownOption = "//li/a[text()='%s']";

    String productName = "pshasamAuto_", createdManufacturingItemName;

    public E2EActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Login to Aspire application
    public void login() {
        browserUtils.waitForElement(emailField);
        emailField.sendKeys("user@aspireapp.com");
        passwordField.sendKeys("@sp1r3app");
        loginButton.click();
    }

    //Create a product from Inventory menu
    public void createProductFromInventory() {
        browserUtils.waitForElement(inventoryOption);
        inventoryOption.click();
        browserUtils.waitForElement(inventoryActiveHeader);
        WebElement menuOption = driver.findElement(By.xpath(String.format(menu, "Products")));
        browserUtils.waitForElement(menuOption);
        menuOption.click();
        WebElement subMenuOption = driver.findElement(By.xpath(String.format(subMenu, "Products")));
        browserUtils.waitForElement(subMenuOption);
        subMenuOption.click();
        browserUtils.waitForElement(productsActiveHeader);
        createProduct();
        navigateToApplications();
    }

    //Create a manufacturing item with created product
    public void createManufacturingItem() {
        browserUtils.waitForElement(manufacturingOption);
        manufacturingOption.click();
        createItem();
        selectProductForManufacturingItem();
        addConsumerToMFItem();
        browserUtils.waitForElement(MOFQuantity);
        MOFQuantity.clear();
        MOFQuantity.sendKeys("50");
        saveButton.click();
        browserUtils.waitForElement(editButton);
        browserUtils.waitForElement(markAsDoneButton);
        markAsDoneButton.click();
        browserUtils.waitForElement(scrapButton);
        browserUtils.waitForElement(doneStatus);
    }

    public void createItem() {
        browserUtils.waitForElement(createButton);
        createButton.click();
    }

    //Select created product to manufacturing item
    public void selectProductForManufacturingItem() {
        browserUtils.waitForElement(productForMF);
        productForMF.sendKeys(productName);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath(String.format(dropdownOption, productName))).click();
        addBillMaterial();
    }

    //Add material bill to the item
    public void addBillMaterial() {
        browserUtils.waitForElement(billMaterial);
        billMaterial.sendKeys(productName);
        addLineToConsume.click();
        browserUtils.waitForElement(BOMCreate);
        BOMCreate.click();
        browserUtils.waitForElement(bomProductQuantity);
        bomProductQuantity.clear();
        bomProductQuantity.sendKeys("50");
        BOFSaveButton.click();
    }

    //Providing consume count to the product
    public void addConsumerToMFItem() {
        browserUtils.waitForElement(addLineToConsume);
        addLineToConsume.click();
        browserUtils.waitForElement(consumerTableProductId);
        BrowserUtils.wait.until(ExpectedConditions.elementToBeClickable(consumerTableProductId));
        consumerTableProductId.sendKeys(productName);
        productForMF.click();
        BOMCreate.click();
        browserUtils.waitForElement(consumeQuantityMOF);
        consumeQuantityMOF.clear();
        consumeQuantityMOF.sendKeys("50");
        confirmButton.click();
        browserUtils.waitForElement(markAsDoneButton);
        createdManufacturingItemName = createdMFItem.getText();
    }

    public void createProduct() {
        browserUtils.waitForElement(createButton);
        createButton.click();
        createRandomProductName();
        browserUtils.waitForElement(productNameField);
        productNameField.sendKeys(productName);
        updateQuantity();
    }

    //Provide the quantity to the product
    public void updateQuantity() {
        browserUtils.waitForElement(updateQuantityButton);
        updateQuantityButton.click();
        browserUtils.waitForElement(createButton);
        createButton.click();
        selectLocation();
        browserUtils.waitForElement(inventoryQuantityField);
        inventoryQuantityField.clear();
        inventoryQuantityField.sendKeys("50");
        saveButton.click();
    }

    //Selecting location for the product
    public void selectLocation() {
        browserUtils.waitForElement(locationDropdown);
        locationDropdown.click();
        browserUtils.waitForElement(whStockLocationOption);
        whStockLocationOption.click();
    }

    public void navigateToApplications() {
        browserUtils.waitForElement(applicationsButton);
        applicationsButton.click();
    }

    //Generate a dynamic product name with pshasamAuto_ as a prefix
    public void createRandomProductName() {
        Random r = new Random();
        productName = productName + r.nextInt(500);
    }

    //Verify created manufacturing item with provided data
    public void verifyCreatedManufacturingItem() {
        browserUtils.waitForElement(createdMFItem);
        Assert.assertTrue(createdMFItem.getText().equals(createdManufacturingItemName));
        Assert.assertTrue(MFProvidedQuantity.getText().equals("50.00"));
        Assert.assertTrue(MFActualQuantity.getText().equals("50.00"));
        Assert.assertEquals(driver.findElements(By.xpath("//div[@class='o_Message_trackingValueNewValue o_Message_trackingValueItem']")).get(0).getText(), "Done");
    }
}
