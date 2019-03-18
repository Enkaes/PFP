package test;

import AppEnv.DevEnv;
import AppEnv.Helper.Login;
import AppEnv.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewClient {



    public static void main(String[] args){

//        Login.login();

        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(DevEnv.baseUrl());

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(User.name);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(User.password);
        driver.findElement(By.cssSelector("button")).click();


        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("app-sidebar-nav")));

        driver.findElement(By.cssSelector("ul.top a[title='Accounts']")).click();
        driver.findElement(By.cssSelector(".navigation li:nth-child(3) a[title=Clients]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));


        WebElement css = driver.findElement(By.cssSelector("a.btn.btn-primary.btn-main"));
        css.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("account-create")));
        driver.findElement(By.cssSelector("input[type='text']:nth-of-type(2)")).sendKeys("Name");




//      driver.findElement(By.cssSelector(".").sendKeys("Test");


//      driver.findElement(By.cssSelector("firstnamefield")).sendKeys("Test");
        driver.findElement(By.cssSelector("lastnamefield")).sendKeys("Usercreated");
        driver.findElement(By.cssSelector("emailfield")).sendKeys("emailaddresswithrandomvariable");
        driver.findElement(By.cssSelector("submit")).click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("title")));

//      assertEquals(ExpectedConditions.presenceOfElementLocated(By.className("messagehere")));



    }
}
