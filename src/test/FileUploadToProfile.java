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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class FileUploadToProfile {


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");
        WebDriver driver = new ChromeDriver();

        //        Login.login();

        driver.get(DevEnv.baseUrl());

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(User.name);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(User.password);
        driver.findElement(By.cssSelector("button")).click();


        WebDriverWait wait= new WebDriverWait(driver, 12);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("app-header")));

        driver.findElement(By.cssSelector("ul.top a[title='Accounts']")).click();
        driver.findElement(By.cssSelector(".navigation li:nth-child(3) a[title=Clients]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));

        driver.findElement(By.cssSelector("tbody tr:nth-child(1)")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.btn-group:nth-child(2) a.btn.btn-secondary:first-child")));

        driver.findElement(By.cssSelector("div.btn-group:nth-child(2) a.btn.btn-secondary:first-child")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h5.title")));


        WebElement upload = driver.findElement(By.cssSelector(".group-layout div.row:nth-child(5) div.col-6:first-child div.document.form-group"));
        upload.click();
        uploadFile("/home/marcin/Videos/IP.png");


        //TODO:
        //one more click needed
        // if for empty upload field container

    }

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public static void uploadFile(String fileLocation) {
        try {
            //Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER); //2 enters to add?
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

}
