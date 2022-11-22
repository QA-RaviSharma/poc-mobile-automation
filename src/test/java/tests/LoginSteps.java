package tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.login.LoginRequest;
import org.junit.Assert;
import org.openqa.selenium.By;
import utilities.Configloader;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static helpers.MobileHelper.*;
import static utilities.Configloader.log;

public class LoginSteps {
    public static String emailId;
    // private LoginRequest loginRequest = new LoginRequest();
    static String fetchedErrorMessages;
    static int i = 0;

    {
        connectLocalAndriodDevice();

    }

    @When("User enters valid details in email and password fields")
    public void userEntersValidDetailsInEmailAndPasswordFields(List<LoginRequest> loginRequestList) {
        if (loginRequestList != null) {
            if ((loginRequestList.get(0).getEmail()!=null || loginRequestList.get(0).getPassword()!=null) && loginRequestList.get(0).getEmail().equals("operatorValidEmailId")) {
                log.info("User's emailId: {} and password: {}", Configloader.property().getProperty("baseSeller"), Configloader.property().getProperty("baseSellerPassword"));
                androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your email']")).sendKeys(Configloader.property().getProperty("baseSeller"));
                androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your password']")).sendKeys(Configloader.property().getProperty("baseSellerPassword"));
            } else if (loginRequestList.get(0).getEmail().equals("vendorValidEmailId")) {
                log.info("User's emailId: {} and password: {}", Configloader.property().getProperty("baseVendor"), Configloader.property().getProperty("baseVendorPassword"));
                androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your email']")).sendKeys(Configloader.property().getProperty("baseVendor"));
                androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your password']")).sendKeys(Configloader.property().getProperty("baseVendorPassword"));
            } else if (loginRequestList.get(0).getEmail().equals("adminValidEmailId")) {
                log.info("User's emailId: {} and password: {}", Configloader.property().getProperty("systemAdmin"), Configloader.property().getProperty("systemAdminPassword"));
                androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your email']")).sendKeys(Configloader.property().getProperty("systemAdmin"));
                androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your password']")).sendKeys(Configloader.property().getProperty("systemAdminPassword"));
            } else {
                log.info("User's emailId: {} and password: {}", loginRequestList.get(0).getEmail(), loginRequestList.get(0).getPassword());
                androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your email']")).sendKeys(loginRequestList.get(0).getEmail());
                androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your password']")).sendKeys(loginRequestList.get(0).getPassword());


                /*This code will work with any browser app*/
                /*if (loginRequestList.get(0).getEmail().equals("empty")) {
                    androidDriver.findElement(By.id("email")).click();
                    androidDriver.findElement(By.id("password")).click();
                    fetchedErrorMessages = androidDriver.findElement(By.id("emailError")).getText();
                } else if (loginRequestList.get(0).getEmail().equals("userValidEmailId")) {
                    androidDriver.findElement(By.id("email")).sendKeys(Configloader.property().getProperty("baseSeller"));
                    androidDriver.findElement(By.id("password")).sendKeys(Configloader.property().getProperty("baseVendorPassword"));
                    androidDriver.findElement(By.id("primaryActionLogin")).click();
                    fetchedErrorMessages = androidDriver.findElement(By.cssSelector(".Polaris-Banner__Heading>p")).getText();
                } else if (loginRequestList.get(0).getEmail().equals("abc@domain.com")) {
                    androidDriver.findElement(By.id("email")).sendKeys(loginRequestList.get(0).getEmail());
                } else {
                    androidDriver.findElement(By.id("email")).sendKeys(loginRequestList.get(0).getEmail());
                    androidDriver.findElement(By.id("password")).click();
                    fetchedErrorMessages = androidDriver.findElement(By.id("emailError")).getText();
                }

                if (loginRequestList.get(0).getPassword().equals("empty")) {
                    androidDriver.findElement(By.id("password")).click();
                    androidDriver.findElement(By.id("email")).click();
                    fetchedErrorMessages = androidDriver.findElement(By.id("passwordError")).getText();
                }*/

            }
        }
    }

    @And("User clicks on login button on marketcube login page")
    public void userClicksOnLoginButtonOnMarketcubeLoginPage() {
        androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Login']")).click();
    }

    @Then("User should be logins in marketcube successfully and able to see the home page")
    public void userShouldBeLoginsInMarketcubeSuccessfullyAndAbleToSeeTheHomePage() {
        try {
            androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Assert.assertTrue(androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Home']")).isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAndQuitSession();
        }
    }

    @Then("User should not be login in marketcube successfully and get validation messages")
    public void userShouldNotBeLoginInMarketcubeSuccessfullyAndGetValidationMessages(List<String> errorMessage) {
        try {
            fetchedErrorMessages = androidDriver.findElement(By.id("android:id/message")).getText();
            log.info("Error message is: {}", fetchedErrorMessages);
            Assert.assertEquals(errorMessage.get(i), fetchedErrorMessages);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            i++;
            closeAndQuitSession();
        }
    }

    @When("User logins in marketcube successfully as operator and able to see the home page")
    public void userLoginsInMarketcubeSuccessfullyAsOperatorAndAbleToSeeTheHomePage() {
        androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your email']")).sendKeys(Configloader.property().getProperty("baseSeller"));
        androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your password']")).sendKeys(Configloader.property().getProperty("baseSellerPassword"));
        userClicksOnLoginButtonOnMarketcubeLoginPage();
        try {
            androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Assert.assertTrue(androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Home']")).isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emailId = Configloader.property().getProperty("baseSeller");
        }
    }

    @When("User logins in marketcube successfully as vendor and able to see the home page")
    public void userLoginsInMarketcubeSuccessfullyAsVendorAndAbleToSeeTheHomePage() {
        androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your email']")).sendKeys(Configloader.property().getProperty("baseVendor"));
        androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your password']")).sendKeys(Configloader.property().getProperty("baseVendorPassword"));
        userClicksOnLoginButtonOnMarketcubeLoginPage();
        try {
            androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Assert.assertTrue(androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Home']")).isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emailId = Configloader.property().getProperty("baseVendor");
        }
    }

    @When("User logins in marketcube successfully as system-admin and able to see the home page")
    public void userLoginsInMarketcubeSuccessfullyAsSystemAdminAndAbleToSeeTheHomePage() {
        androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your email']")).sendKeys(Configloader.property().getProperty("systemAdmin"));
        androidDriver.findElement(By.xpath("//android.widget.EditText[@text='Enter your password']")).sendKeys(Configloader.property().getProperty("systemAdminPassword"));
        userClicksOnLoginButtonOnMarketcubeLoginPage();
        try {
            androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            Assert.assertTrue(androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Home']")).isDisplayed());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emailId = Configloader.property().getProperty("systemAdmin");
        }
    }
}
