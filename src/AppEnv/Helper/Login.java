package AppEnv.Helper;

import AppEnv.DevEnv;
import AppEnv.User;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(DevEnv.baseUrl());

    }

    public static void login() {

        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(DevEnv.baseUrl());


        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(User.name);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(User.password);
        driver.findElement(By.cssSelector("button")).click();

        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("app-sidebar-nav")));
    }

}

