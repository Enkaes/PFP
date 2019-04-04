package test.RegressionCases;

import AppEnv.DevEnv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignupFlowTests {


    public WebDriver driver;

    @BeforeTest
    public void setup(){

//        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");                  //Ubuntu
        System.setProperty("webdriver.chrome.driver", "C://Users/Marcin/IdeaProjects/driver/chromedriver.exe");         //Winda

        driver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void signUpCheck(){

        driver.get(DevEnv.baseUrl() + "signup");

        driver.findElement(By.cssSelector("div.form-group input.form-control.form-control-lg")).sendKeys("RegressionCompany");
        driver.findElement(By.cssSelector("input[type='first_name']")).sendKeys("RegressionName");
        driver.findElement(By.cssSelector("input[type='last_name']")).sendKeys("RegressionSurname");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("m.limont+462@precisefp.com");
        driver.findElement(By.cssSelector("div.field-phone.input-group.input-group-lg input.form-control.rounded-right")).sendKeys("3123121232");
        driver.findElement(By.cssSelector(".app-button.btn.btn-block.btn-primary.btn-lg")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert(driver.findElement(By.cssSelector("h3.text-uppercase.text-center.text-muted.mb-4")).isDisplayed());

        if(driver.findElement(By.cssSelector("h3.text-uppercase.text-center.text-muted.mb-4")).isDisplayed()) {
            System.out.println("Company Created Successfully");
        }else {
            System.out.println("Company Creation Failed");
        }
    }

    @Test(priority = 2)
    public void resetPasswordFlow(){

        driver.get(DevEnv.baseUrl() + "signin");

        driver.findElement(By.cssSelector("a.text-secondary")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("m.limont+4@precisefp.com");
        driver.findElement(By.cssSelector("button.app-button.btn.btn-block.btn-primary")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(driver.findElement(By.cssSelector("div.alert.alert-success.mb-3")).isDisplayed()){
            System.out.println("Reset Password Email Sent Successfully");
        }else {
            System.out.println(("Password reset failed"));
        }
    }
}
