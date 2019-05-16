package AppEnv;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login extends Setup {

    public static void login() {

        driver.get(Setup.baseUrl());

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(User.name);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(User.password);
        driver.findElement(By.cssSelector("button")).click();

        WebDriverWait wait5 = new WebDriverWait(driver, 5);
        wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("app-header")));

        //Change team if not in correct one
        String title;
        title = driver.findElement(By.cssSelector("div.app-header .title")).getText();

        if (title.contains("AUTOMATION")) {
        } else {
            driver.findElement(By.cssSelector("span.rounded-circle")).click();
            driver.findElement(By.cssSelector("div.media-body")).click();
            driver.findElement(By.cssSelector("div.app-switcher.ps.active li:nth-child(5)")).click();
            wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("app-header")));
        }
    }
}
