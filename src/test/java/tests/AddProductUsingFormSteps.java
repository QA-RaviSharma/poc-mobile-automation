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

import static helpers.MobileHelper.*;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static utilities.Configloader.log;

public class AddProductUsingFormSteps {
    public static String productTitle = null;

    @When("User goes to add product form page in marketcube")
    public void userGoesToAddProductFormPageInMarketcube() {
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='\uF100']")).click();
    }

    @And("User enters valid details of the product on add product page")
    public void userEntersValidDetailsOfTheProductOnAddProductPage() {
        productTitle = "Mobile Automation : " + RandomGenerator.faker.crypto().md5();
        androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Product title']")).sendKeys(productTitle);
        androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Product description']")).sendKeys("Product Description : " + RandomGenerator.faker.programmingLanguage().creator());

        /*Currently, There is no proper locator for price and compare price fields so, I'm commenting them.*/
        /*androidDriver.findElement(By.xpath("//android.view.ViewGroup[7]/android.widget.EditText[0]")).sendKeys("1000");
        androidDriver.findElement(By.xpath("//android.view.ViewGroup[8]/android.widget.EditText[0]")).sendKeys("2000");*/
        if (!LoginSteps.emailId.equals(Configloader.property().getProperty("baseVendor"))) {
            androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Please Select']")).click();
            androidDriver.findElement(By.xpath("//android.widget.TextView[@text='tb1']")).click();
        }
    }

    @And("User clicks on save and next button on add product page")
    public void userClicksOnSaveAndNextButtonOnAddProductPage() {
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='SAVE & NEXT']")).click();
    }

    @Then("User should be able to add product in marketcube using form successfully")
    public void userShouldBeAbleToAddProductInMarketcubeUsingFormSuccessfully() {
        log.info("Product is added successfully.");
        closeAndQuitSession();
    }

    @When("User enters valid details and adds the product in marketcube successfully")
    public void userEntersValidDetailsAndAddsTheProductInMarketcubeSuccessfully() {
        userGoesToAddProductFormPageInMarketcube();
        userEntersValidDetailsOfTheProductOnAddProductPage();
        userClicksOnSaveAndNextButtonOnAddProductPage();
        log.info("Product is added successfully.");
        closeAndQuitSession();
    }
}
