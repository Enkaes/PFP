package AppEnv;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Setup {

    private static final String DOMAIN = "https://app.precisefp";
    private static final String PORT = ".com/";
    public static String baseUrl() {
        return(DOMAIN+PORT);
    }

    protected static WebDriver driver;

    private static void setChromeDriverProperty(){
        //Ubuntu
//        System.setProperty("webdriver.chrome.driver", "/home/marcin/IdeaProjects/tests/chromedriver");

        //Windows
        System.setProperty("webdriver.chrome.driver", "C://Users/Marcin/IdeaProjects/driver/chromedriver.exe");
    }

    @BeforeMethod
    protected static void login(){

        setChromeDriverProperty();
        driver = new ChromeDriver();

        driver.get(Setup.baseUrl());

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(User.name);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(User.password);
        driver.findElement(By.cssSelector("button")).click();

        WebDriverWait wait5= new WebDriverWait(driver, 5);
        wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("app-header")));

        //Change team if not in correct one
        String title;
        title = driver.findElement(By.cssSelector("div.app-header .title")).getText();

        if(title.contains("AUTOMATION")){
        }else{
            driver.findElement(By.cssSelector("span.rounded-circle")).click();
            driver.findElement(By.cssSelector("div.media-body")).click();
            driver.findElement(By.cssSelector("div.app-switcher.ps.active li:nth-child(5)")).click();
            wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("app-header")));
        }
    }

    @AfterMethod
    public static void tearDown(){
        driver.close();
    }
}
