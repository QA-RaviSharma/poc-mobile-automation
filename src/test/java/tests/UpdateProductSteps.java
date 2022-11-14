package tests;

import helpers.RandomGenerator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Configloader;

import java.util.concurrent.TimeUnit;

import static helpers.MobileHelper.androidDriver;
import static helpers.MobileHelper.closeAndQuitSession;
import static utilities.Configloader.log;

public class UpdateProductSteps {
    @When("User enters valid inventory details")
    public void userEntersValidInventoryDetails() {
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        androidDriver.findElement(By.id("sku")).sendKeys("SKU : "+ RandomGenerator.faker.number().digits(7));
        androidDriver.findElement(By.id("barcode")).sendKeys("Barcode : "+RandomGenerator.faker.number().digits(7));
        androidDriver.findElement(By.id("inventoryQuantity")).sendKeys(RandomGenerator.faker.number().digits(4));
    }

    @And("User clicks on save and next button to update the inventory details")
    public void userClicksOnSaveAndNextButtonToUpdateTheInventoryDetails() {
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        androidDriver.findElement(By.cssSelector(".Polaris-Button--primary")).click();
    }

    @Then("Inventory details should be updated in the product successfully")
    public void inventoryDetailsShouldBeUpdatedInTheProductSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, 20);
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Polaris-Frame-Toast"))).getText();
            //String text = androidDriver.findElement(By.cssSelector(".Polaris-Frame-Toast")).getText();
            Assert.assertEquals(text, "Inventory is updated successfully.");
        } catch (Exception e) {
            log.error("Cause : {}", e.getCause());
            log.error("Message : {}", e.getMessage());
            e.printStackTrace();
        } finally {
            closeAndQuitSession();
        }
    }

    @When("User enters valid fulfillment details")
    public void userEntersValidFulfillmentDetails() throws InterruptedException {
        log.info("I'm waiting!!!");
        Thread.sleep(5000);
        log.info("Wait Done!!!");
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        js.executeScript("window.scrollTo( screen.width/2, screen.height/2 )");
        androidDriver.findElement(By.xpath("(//button[@class='Polaris-Tabs__DisclosureActivator']/span[@class='Polaris-Tabs__Title'])[4]")).click();
        androidDriver.findElement(By.id("fulfillment")).click();
        androidDriver.findElement(By.id("weight")).sendKeys("100");
        androidDriver.findElement(By.cssSelector("option[value='kg']")).click();
        if (LoginSteps.emailId.equals(Configloader.property().getProperty("baseVendor"))) {
            androidDriver.findElement(By.id("length")).sendKeys("10");
            androidDriver.findElement(By.id("width")).sendKeys("10");
            androidDriver.findElement(By.id("height")).sendKeys("10");
        }
    }

    @And("User clicks on save and next button to update the fulfillment details")
    public void userClicksOnSaveAndNextButtonToUpdateTheFulfillmentDetails() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(3000);
        androidDriver.findElement(By.xpath("(//span[@class='Polaris-Button__Content'])[2]")).click();
    }

    @Then("Fulfillment details should be updated in the product successfully")
    public void fulfillmentDetailsShouldBeUpdatedInTheProductSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, 20);
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Polaris-Frame-Toast"))).getText();
            //String text = androidDriver.findElement(By.cssSelector(".Polaris-Frame-Toast")).getText();
            Assert.assertEquals(text, "Fulfillment is updated successfully.");
        } catch (Exception e) {
            log.error("Cause : {}", e.getCause());
            log.error("Message : {}", e.getMessage());
            e.printStackTrace();
        } finally {
            closeAndQuitSession();
        }
    }
}
