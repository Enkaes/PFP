package test;

import AppEnv.Helper.Login;
import AppEnv.Helper.WaitForReload;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import AppEnv.DevEnv;
import AppEnv.User;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    @Test
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get(DevEnv.baseUrl());

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(User.name);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(User.password);
        driver.findElement(By.cssSelector("button")).click();

        Login.login();


//      WaitForReload(WebDriver driver = new ChromeDriver());

//      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.presenceOfElementLocated());

        assertEquals(DevEnv.baseUrl()+"dashboard", driver.getCurrentUrl());

        driver.quit();

    }
}
