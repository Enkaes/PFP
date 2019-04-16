package test;

import AppEnv.Setup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MethodsPlayground {

    @Test
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get(Setup.baseUrl());

    }

    // select methods 101
    public void selectMethods() throws InterruptedException

    {
        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(Setup.baseUrl());


//      title, id or class
        driver.findElement(By.cssSelector("button[title=\"New Account\"]"));
        driver.findElement(By.cssSelector("input[id=email]"));
        driver.findElement(By.className(".app-table"));

        driver.findElement(By.cssSelector("button[title='ButtonText']")).click();
        driver.findElement(By.cssSelector("ul.top a[title='Accounts']")).click();

        driver.findElement(By.cssSelector("input[type='text']:first-of-type")).sendKeys("Name");   //first of many of the same type
        driver.findElement(By.cssSelector("input[type='text']:nth-of-type(3)")).sendKeys("Name");  // third of many of same tye


        //SELECT:
        WebElement element=driver.findElement(By.cssSelector("input[type='list']"));
        Select se=new Select(element);

        se.selectByValue("Client");      //deselectBy also possible to deselect previously selected
        se.selectByIndex(1);
        se.selectByVisibleText("Client");

        //select element and count children, needs select tag, not div though, works for dropdowns   getOptions below
        WebElement selectElement = driver.findElement(By.cssSelector(".group-layout div.row:nth-child(5) div.list-group.list-group-flush"));
        Select listBox = new Select(selectElement);
        int fieldcount =  listBox.getOptions().size();
    }

    public void getDropdownOptions(){

        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get(Setup.baseUrl());

        WebElement dropdown=driver.findElement(By.id("dropdown"));   //finds dropdown by id, lists all available values and prints them
        Select se=new Select(dropdown);
        List<WebElement> allOptions = se.getOptions();
        for(WebElement webElement : allOptions)
        {
            System.out.println((webElement.getText()));
        }
    }

    public void expectedConditionsExamples(){

        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(Setup.baseUrl());

        WebDriverWait wait= new WebDriverWait(driver, 5);
//        wait.until(ExpectedConditions.isDisplayed());̣
        wait.until((ExpectedConditions.presenceOfElementLocated(By.cssSelector("logo"))));
        // or

        WebElement element = driver.findElement(By.id("id"));
        element.isDisplayed();
        element.isEnabled();





    }
}
