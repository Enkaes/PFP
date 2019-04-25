package AppEnv.Helper;

import AppEnv.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navigator extends Setup {

    //Navigates to Profile under Settings
    public void navigateToProfile(){
        WebDriverWait wait3 = new WebDriverWait(driver, 3);
        driver.findElement(By.cssSelector("span.rounded-circle")).click();
        driver.findElement(By.cssSelector("div.panel.active div.navigation li")).click();
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='first_name']")));
    }
    //Navigates to All Engagements list
    public void navigateToEngagements(){
        WebDriverWait wait3 = new WebDriverWait(driver, 3);
        driver.findElement(By.cssSelector("ul.top li:nth-child(2)")).click();
        driver.findElement(By.cssSelector("div.panel.active div.navigation li:first-child")).click();
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));
    }
    //Navigates to Clients list
    public void navigateToClients(){
        WebDriverWait wait3 = new WebDriverWait(driver, 3);
        driver.findElement(By.cssSelector("ul.top a:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".navigation li:nth-child(3)")).click();
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));
    }
    //Navigates to Teams list
    public void navigateToTeams() {
        WebDriverWait wait3 = new WebDriverWait(driver, 3);
        try {
            driver.findElement(By.cssSelector("div.panel.active div.navigation:nth-child(3) li")).click();
        } catch (NoSuchElementException e) {
            driver.findElement(By.cssSelector("span.rounded-circle")).click();
            driver.findElement(By.cssSelector("div.panel.active div.navigation:nth-child(3) li")).click();
        }
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));
    }
    //Navigates to Subscription
    public void navigateToSubscription() {
        WebDriverWait wait3 = new WebDriverWait(driver, 3);

        try {
            driver.findElement(By.cssSelector("div.panel.active div.navigation:nth-child(4) li")).click();
        } catch (NoSuchElementException e) {
            driver.findElement(By.cssSelector("span.rounded-circle")).click();
            driver.findElement(By.cssSelector("div.panel.active div.navigation:nth-child(4) li")).click();
        }
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-button.btn.btn-danger")));
    }
    //Navigates to Users
    public void navigateToUsers() {
        WebDriverWait wait3 = new WebDriverWait(driver, 3);

        try {
            driver.findElement(By.cssSelector("div.panel.active div.navigation:nth-child(3) li:nth-child(2)")).click();
        } catch (NoSuchElementException e) {
            driver.findElement(By.cssSelector("span.rounded-circle")).click();
            driver.findElement(By.cssSelector("div.panel.active div.navigation:nth-child(3) li:nth-child(2)")).click();
        }
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));
    }
}
