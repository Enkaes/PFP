package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PrecisePing {


 public static void main(String[] args)

        {

            System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");

            WebDriver driver = new ChromeDriver();

            driver.get("http://dev.precisefp.com/signin");

            driver.findElement(By.cssSelector("login")).sendKeys("test");

            driver.findElement(By.cssSelector("password")).sendKeys("abcd");

            driver.findElement(By.cssSelector("signin")).click();

            System.out.println("Successful");

            driver.quit();

        }
    }
