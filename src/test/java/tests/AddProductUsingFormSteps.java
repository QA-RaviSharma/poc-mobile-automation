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

public class AddProductUsingFormSteps {

    @When("User goes to add product form page in marketcube")
    public void userGoesToAddProductFormPageInMarketcube() {
        androidDriver.findElement(By.xpath("(//button[@class='Polaris-Tabs__DisclosureActivator']/span[@class='Polaris-Tabs__Title'])[2]")).click();
        androidDriver.findElement(By.id("Products")).click();
        androidDriver.findElement(By.xpath("//span[text()='Add Products']")).click();
        androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        androidDriver.findElement(By.id("addProduct")).click();

    }

    @And("User selects create product using form option from dropdown")
    public void userSelectsCreateProductUsingFormOptionFromDropdown() {
        androidDriver.findElement(By.xpath("//option[@value='createForm']")).click();
    }

    @And("User enters valid details of the product on add product page")
    public void userEntersValidDetailsOfTheProductOnAddProductPage() {
        androidDriver.findElement(By.xpath("(//input[@class='Polaris-TextField__Input'])[1]")).sendKeys("Mobile Automation : " + RandomGenerator.faker.crypto().md5());
        androidDriver.switchTo().frame("idTiny_ifr").findElement(By.id("tinymce")).sendKeys("This Product Is Added On : " + java.time.LocalDateTime.now());
        androidDriver.switchTo().parentFrame();
        androidDriver.findElement(By.id("price")).sendKeys("1000");
        androidDriver.findElement(By.id("comparePrice")).sendKeys("2000");
        if (!LoginSteps.emailId.equals(Configloader.property().getProperty("baseVendor"))) {
            androidDriver.findElement(By.xpath("//option[text()='tb1']")).click();
        }
    }

    @And("User clicks on save and next button on add product page")
    public void userClicksOnSaveAndNextButtonOnAddProductPage() {
        JavascriptExecutor js = (JavascriptExecutor) androidDriver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        androidDriver.findElement(By.xpath("//span[text()='Save & Next']")).click();
    }

    @Then("User should be able to add product in marketcube using form successfully")
    public void userShouldBeAbleToAddProductInMarketcubeUsingFormSuccessfully() {
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, 20);
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Polaris-Frame-Toast"))).getText();
            //String text = androidDriver.findElement(By.cssSelector(".Polaris-Frame-Toast")).getText();
            Assert.assertEquals(text, "Product is added successfully.");
        } catch (Exception e) {
            log.error("Cause : {}", e.getCause());
            log.error("Message : {}", e.getMessage());
            e.printStackTrace();
        } finally {
            closeAndQuitSession();
        }
    }

    @When("User enters valid details and adds the product in marketcube successfully")
    public void userEntersValidDetailsAndAddsTheProductInMarketcubeSuccessfully() {
        userGoesToAddProductFormPageInMarketcube();
        userSelectsCreateProductUsingFormOptionFromDropdown();
        userEntersValidDetailsOfTheProductOnAddProductPage();
        userClicksOnSaveAndNextButtonOnAddProductPage();
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, 20);
            String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Polaris-Frame-Toast"))).getText();
            // String text = androidDriver.findElement(By.cssSelector(".Polaris-Frame-Toast")).getText();
            Assert.assertEquals(text, "Product is added successfully.");
        } catch (Exception e) {
            log.error("Cause : {}", e.getCause());
            log.error("Message : {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
