package test.RegressionCases;

import AppEnv.Helper.Login;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateNewClient {

    public WebDriver driver;

    @BeforeTest
    public void setup() {

        Login.login();
    }

    @Test(priority=1)
    public void createClientTest(){

//        WebDriverWait wait = new WebDriverWait(driver, 8);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("app-sidebar-nav")));

        driver.findElement(By.cssSelector("ul.top a:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".navigation li:nth-child(3)")).click();

        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));

        WebElement css = driver.findElement(By.cssSelector("a.btn.btn-primary.btn-main"));
        css.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("account-create")));
        driver.findElement(By.cssSelector("input[type='text']:nth-of-type(2)")).sendKeys("UserName");


        driver.findElement(By.cssSelector("lastnamefield")).sendKeys("Usercreated");
        driver.findElement(By.cssSelector("emailfield")).sendKeys("m.limont+"+ RandomString.make(3) + "@precisefp.com");
        driver.findElement(By.cssSelector("submit")).click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("title")));

//      assertEquals(ExpectedConditions.presenceOfElementLocated(By.className("messagehere")));
//        Assert.assertTrue("Client added successfully", clientcount1 < clientcount2);



    }
}
