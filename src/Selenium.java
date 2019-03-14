import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;



public class Selenium

{

    public static void main(String[] args)

    {

        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("http://demo.testarena.pl/zaloguj");

        driver.findElement(By.id("email")).sendKeys("administrator@testarena.pl");

        driver.findElement(By.id("password")).sendKeys("sumXQQ72$L");

                driver.findElement(By.id("login")).click();

        System.out.println("Poprawne logowanie");

        driver.quit();

        }

        public static void check(){

        WebDriver driver = new ChromeDriver();

        }


    }