package test.RegressionCases;

import AppEnv.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.testng.AssertJUnit.assertTrue;

public class EngagementTests extends Setup {

    @Test(priority = 1)
    public void createEngagement() {

        WebDriverWait wait3 = new WebDriverWait(driver, 3);

        driver.findElement(By.cssSelector("ul.top li:nth-child(2)")).click();
        driver.findElement(By.cssSelector("div.panel.active div.navigation li:first-child")).click();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));

        //Open sidebar, select engagement
        driver.findElement(By.cssSelector("a.btn.btn-primary.btn-main")).click();
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.engagement-create div.form-row div.col-6:first-child")));
        driver.findElement(By.cssSelector("div.engagement-create div.form-row div.col-6:first-child")).click();


        //Select Addressee
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".multiselect__single")));
        driver.findElement(By.cssSelector(".multiselect__single")).click();

        WebElement search = driver.findElement(By.cssSelector(".multiselect__input"));
        search.sendKeys("UserSurname");

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".multiselect__option.multiselect__option--highlight")));
        driver.findElement(By.cssSelector(".multiselect__option.multiselect__option--highlight")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("button.app-button.btn.btn-primary")).click();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-sm.btn-outline-secondary")));
        driver.findElement(By.cssSelector("button.btn.btn-sm.btn-outline-secondary")).click();
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(driver.findElement(By.cssSelector(".app-header div.flash div.alert.col-12.fade.show.col-md-8.col-xl-6.col-xxl-4.alert-success")).isDisplayed()){
            System.out.println("Engagement Created Successfully");
        }else {
            System.out.println(("Engagement was not created"));
        }
    }

    @Test(priority = 2)
    public void accessEngagement(){

        WebDriverWait wait3 = new WebDriverWait(driver, 3);

        driver.findElement(By.cssSelector("ul.top a:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".navigation li:nth-child(3)")).click();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));

        driver.findElement(By.cssSelector("div.app-table tbody tr:first-child")).click();

        String pin;
        pin = driver.findElement(By.cssSelector(".text-muted.px-1")).getText();

        System.out.println(pin);


        //possible problem with Engage button
        driver.findElement(By.cssSelector("div.toolbar.btn-toolbar.justify-content-between div.btn-group button.btn.btn-primary")).click();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.engagement-create div.form-row div.col-6:first-child")));

        driver.findElement(By.cssSelector("div.engagement-create div.form-row div.col-6:first-child")).click();
        driver.findElement(By.cssSelector("button.btn.btn-sm.btn-outline-secondary")).click();
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(driver.findElement(By.cssSelector(".app-header div.flash div.alert.col-12.fade.show.col-md-8.col-xl-6.col-xxl-4.alert-success")).isDisplayed()){
            System.out.println("Engagement Created Successfully");
        }else {
            System.out.println(("Engagement was not created"));
        }

        //Access Engagement
        String engagementUrl;
        engagementUrl = driver.findElement(By.cssSelector("input[type='text']")).getText();

        System.out.println(engagementUrl);
//        driver.get(engagementUrl);




    }
}
