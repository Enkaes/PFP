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

        WebDriverWait wait= new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("app-sidebar-nav")));

        driver.navigate().to(DevEnv.baseUrl() + "accounts/clients/1864/profile");

        WebDriverWait wait2 = new WebDriverWait(driver, 8);
        wait2.until(ExpectedConditions.presenceOfElementLocated(By.className("title")));

        WebElement upload = driver.findElement(By.cssSelector("[data-element=febacefe-2596-44f1-8a20-3282670b75b2]"));  // data-element  febacefe-2596-44f1-8a20-3282670b75b2 -> nest document-container empty | if not empty -> nest button below
        upload.click();
        uploadFile("/home/marcin/Pictures/IP.png");



//  TODO  figure out client table selectors
//        WebElement element = driver.findElement(By.cssSelector());
//        element.click();
//        WebElement button = ((ChromeDriver) driver).findElementByName("Profile");
//        button.click();
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
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

}
