package test.VariousCases;

import AppEnv.Helper.EngagementSender;
import AppEnv.Helper.Navigator;
import AppEnv.Setup;
import AppEnv.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class EngagementTests extends Setup {

    private Navigator navigator = new Navigator();
    private EngagementSender sender = new EngagementSender();

    @Test(priority = 1)
    public void createEngagement() {

        WebDriverWait wait3 = new WebDriverWait(driver, 3);

        navigator.navigateToEngagements();

        //Open sidebar, select engagement
        driver.findElement(By.cssSelector("a.btn.btn-primary.btn-main")).click();
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.engagement-create div.form-row div.col-6:first-child")));
        driver.findElement(By.cssSelector("div.engagement-create div.form-row div.col-6:first-child")).click();

        //Select Addressee
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.multiselect")));
        driver.findElement(By.cssSelector("div.multiselect")).click();

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
    public void accessEngagement() {

        WebDriverWait wait3 = new WebDriverWait(driver, 5);

        navigator.navigateToClients();

        driver.findElement(By.cssSelector("div.app-table tbody tr:first-child")).click();

        String pin = "0";
        //check if password was set, get pin
        try {
            driver.findElement(By.cssSelector("span.px-1.text-teal"));
            System.out.println("Secure password already exists");
        } catch (NoSuchElementException e) {
            pin = driver.findElement(By.cssSelector("code.text-muted.px-1")).getText();
        }

        //TODO: use EngagementSender here

        //possible problem with Engage button
        driver.findElement(By.cssSelector("div.toolbar.btn-toolbar.justify-content-between div.btn-group button.btn.btn-primary")).click();

        //Select and create engagement
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.engagement-create div.form-row div.col-6:first-child")));
        driver.findElement(By.cssSelector("div.engagement-create div.form-row div.col-6:first-child")).click();
        driver.findElement(By.cssSelector("button.btn.btn-sm.btn-outline-secondary")).click();
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Access Engagement
        String engagementUrl;
        engagementUrl = driver.findElement(By.cssSelector("input.form-control.text-dark.cursor-select")).getAttribute("value");

        driver.get(engagementUrl);
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.pfp-engagement-footer")));
        driver.findElement(By.cssSelector("button.pfp-engagement-button.start")).click();

        //different behavior if password was set or not
        try {
            driver.findElement(By.cssSelector("input[type='text']")).sendKeys(pin);
            driver.findElement(By.cssSelector("button[type='submit']")).click();

            wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.form-group.pfp-engagement-element:nth-child(3)")));
            driver.findElement(By.cssSelector("input[type='password']")).sendKeys(User.password);
            driver.findElement(By.cssSelector("div.form-group.pfp-engagement-element:nth-child(3) input[type='password']")).sendKeys(User.password);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
        } catch (NoSuchElementException e) {
            driver.findElement(By.cssSelector("input[type='password']")).sendKeys(User.password);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
        }

//      TODO:  sender.accessEngagement();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver.findElement(By.cssSelector("button.pfp-engagement-button.prev")).isDisplayed()) {
            System.out.println("Engagement Accessed Successfully");
        }else{
            System.out.println("Engagement not Accessed");
        }
    }
    //TODO: access blocked acc with password change
}
