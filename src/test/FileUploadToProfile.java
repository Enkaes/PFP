package test;

import AppEnv.Setup;
import AppEnv.User;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class FileUploadToProfile extends Setup{


    public static void main(String[] args) {


        WebDriverWait wait= new WebDriverWait(driver, 8);

        driver.findElement(By.cssSelector("ul.top a[title='Accounts']")).click();
        driver.findElement(By.cssSelector(".navigation li:nth-child(3) a[title=Clients]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));
        driver.findElement(By.cssSelector("tbody tr:nth-child(1)")).click();

        //TODO: implicit or fluent wait here
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.btn-group:nth-child(2) a.btn.btn-secondary:first-child")));
        driver.findElement(By.cssSelector("div.btn-group:nth-child(2) a.btn.btn-secondary:first-child")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h5.title")));

//        WebElement selectElement = driver.findElement(By.cssSelector(".group-layout div.row:nth-child(5) div.list-group.list-group-flush"));
//        Select listBox = new Select(selectElement);
//        int fieldcount =  listBox.getOptions().size();

        //TODO: proper wait
        //add wait to helper
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // count number of children of div
        int filecount = driver.findElements(By.cssSelector(".group-layout div.row:nth-child(5) div.list-group.list-group-flush")).size();


        WebElement docfield = driver.findElement(By.cssSelector(".group-layout div.row:nth-child(5) button.btn.more.btn-block"));
        docfield.click();
        uploadFile("C:\\Users\\Marcin\\Desktop\\różne\\abcd.JPG");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int filecount2 = driver.findElements(By.cssSelector(".group-layout div.row:nth-child(5) div.list-group.list-group-flush")).size();

//        WebElement selectElement2 = driver.findElement(By.cssSelector(".group-layout div.row:nth-child(5) div.list-group.list-group-flush"));
//        Select listBox2 = new Select(selectElement);
//        int fieldcount2 =  listBox.getOptions().size();

        System.out.println(filecount);
        System.out.println(filecount2);

        Assert.assertTrue("Uploaded Successfully" ,filecount2>filecount);
        Assert.assertNotSame(filecount2, filecount);

    }

    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public static void uploadFile(String fileLocation) {
        try {
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
