package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Configloader;

import java.util.concurrent.TimeUnit;

import static helpers.MobileHelper.*;
import static utilities.Configloader.log;

public class Demo_Test {
    @Test
    public static void user_logins_into_marketcube_and_adds_a_product_using_form() {
        try {
            connectLocalAndriodDevice();
            androidDriver.findElement(By.id("email")).sendKeys(Configloader.property().getProperty("baseSeller"));
            androidDriver.findElement(By.id("password")).sendKeys(Configloader.property().getProperty("baseSellerPassword"));
            androidDriver.findElement(By.id("primaryActionLogin")).click();
            androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            androidDriver.findElement(By.xpath("(//button[@class='Polaris-Tabs__DisclosureActivator']/span[@class='Polaris-Tabs__Title'])[2]")).click();
            androidDriver.findElement(By.id("Products")).click();
            androidDriver.findElement(By.xpath("//span[text()='Add Products']")).click();
            androidDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            androidDriver.findElement(By.id("addProduct")).click();
            androidDriver.findElement(By.xpath("//option[@value='createForm']")).click();
            androidDriver.findElement(By.xpath("(//input[@class='Polaris-TextField__Input'])[1]")).sendKeys("Product Title");
            androidDriver.findElement(By.cssSelector("iframe[id^=idTiny_ifr]")).sendKeys("Product Description");
            androidDriver.findElement(By.id("price")).sendKeys("1000");
            androidDriver.findElement(By.id("comparePrice")).sendKeys("2000");
            androidDriver.findElement(By.xpath("//option[text()='tb1']")).click();
            androidDriver.findElement(By.xpath("//span[text()='Save & Next']")).click();
            String text = androidDriver.findElement(By.cssSelector(".Polaris-Frame-Toast")).getText();
            Assert.assertEquals(text, "Product is added successfully.");
            closeAndQuitSession();
        } catch (Exception e) {
            log.error("Cause : {}", e.getCause());
            log.error("Message : {}", e.getMessage());
            e.printStackTrace();
        }
    }
}