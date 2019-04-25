package test.RegressionCases;

import AppEnv.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import AppEnv.Helper.WaitClasses;

import static org.testng.AssertJUnit.assertNotSame;

public class InstallTemplate extends Setup {

    private WaitClasses waiter = new WaitClasses();

    @Test
    public void installTemplate() {

        WebDriverWait wait3 = new WebDriverWait(driver, 3);
        driver.findElement(By.cssSelector("ul.top li:nth-child(4)")).click();
        driver.findElement(By.cssSelector("div.panel.active li:nth-child(5)")).click();
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img.card-img-top")));

        String libraryURL = driver.getCurrentUrl();

        driver.findElement(By.cssSelector("div.col-12.col-md-6.col-lg-4.col-xl-3.col-xxl-2:nth-child(3)")).click();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.app-content-drawer")));
        driver.findElement(By.cssSelector("div.app-content-drawer button[type='button']")).click();

        try {
            waiter.waitForUrlContains("/templates/my", driver, 5);
        } catch (NoSuchElementException e ){
            System.out.println("Template was not installed successfully");
        }

        String URL2 = driver.getCurrentUrl();
        assertNotSame("Template was not installed successfully", libraryURL, URL2);
    }
}
