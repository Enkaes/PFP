package AppEnv.Helper;

import AppEnv.Setup;
import AppEnv.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EngagementSender extends Setup {

    private Navigator navigator = new Navigator();

    public String pin = "0";

    private static String engagementUrl;

    public void sendEngagement() {

        WebDriverWait wait3 = new WebDriverWait(driver, 5);

        //Navigate to clients, select first one and access Profile
        navigator.navigateToClients();
        driver.findElement(By.cssSelector("div.app-table tbody tr:first-child")).click();

        //check if password was set, get pin
        try {
            driver.findElement(By.cssSelector("span.px-1.text-teal"));
            System.out.println("Secure password already exists");
        } catch (NoSuchElementException e) {
            pin = driver.findElement(By.cssSelector("code.text-muted.px-1")).getText();
        }

        driver.findElement(By.cssSelector("div.account-show div.btn-group:nth-child(2) .btn-secondary:first-child")).click();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.calculated.form-group")));

        //Send first engagement in table
        driver.findElement(By.cssSelector("div.btn-group.nav-item.nav-link")).click();
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.engagement-create div.form-row div.col-6:first-child")));
        driver.findElement(By.cssSelector("div.engagement-create div.form-row div.col-6:first-child")).click();
        driver.findElement(By.cssSelector("button.btn.btn-sm.btn-outline-secondary")).click();
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Copy engagement url, close drawer
        engagementUrl = driver.findElement(By.cssSelector("input.form-control.text-dark.cursor-select")).getAttribute("value");
        driver.findElement(By.cssSelector("div.form-row button[type='button']")).click();

    }

    public void getSentEngagementUrl(){
        driver.get(engagementUrl);
    }

    public void accessEngagement(){

        WebDriverWait wait3 = new WebDriverWait(driver, 5);

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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
