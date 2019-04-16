package AppEnv.Helper;

import AppEnv.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClientPanelAccess extends Setup{

    public void accessClients(){

        driver.findElement(By.cssSelector("ul.top a:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".navigation li:nth-child(3)")).click();
    }
}
