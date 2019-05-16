package test.VariousCases;

import AppEnv.Helper.Navigator;
import AppEnv.Setup;
import AppEnv.User;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;


public class CreateNewClient extends Setup {

    private Navigator navigator = new Navigator();

    @Test(priority=1)
    public void createClientTest() {

        navigator.navigateToClients();

        int rowCount1 = 0;

        if(driver.findElement(By.cssSelector(".app-table")).isDisplayed()){
            rowCount1=driver.findElements(By.cssSelector(".table.table-hover tbody tr")).size();
        }else{
            System.out.println(("Element not present, table empty"));
        }

        WebElement css = driver.findElement(By.cssSelector("a.btn.btn-primary.btn-main"));
        css.click();

        driver.findElement(By.cssSelector("div.container.mt-3 form > div:nth-child(4) div:nth-child(1) .form-control")).sendKeys("AutCreated");
        driver.findElement(By.cssSelector("div.container.mt-3 form > div:nth-child(4) div:nth-child(2) .form-control")).sendKeys("UserSurname");
        driver.findElement(By.cssSelector("div.container.mt-3 form > div:nth-child(5) div:nth-child(1) .form-control")).sendKeys("m.limont+"+ RandomString.make(4) + "@precisefp.com");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int rowCount2=driver.findElements(By.cssSelector(".table.table-hover tbody tr")).size();

        assertTrue("Client not on the table", rowCount1 < rowCount2);

        if(driver.findElement(By.cssSelector(".app-header div.flash div.alert.col-12.fade.show.col-md-8.col-xl-6.col-xxl-4.alert-success")).isDisplayed()){
            System.out.println("Client Created Successfully");
        }else {
            System.out.println(("Client creation process failed"));
        }
    }

    @Test(priority=2)
    public void createWithExistingEmail(){

        WebDriverWait wait3 = new WebDriverWait(driver, 3);

        navigator.navigateToClients();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));

        String email;
        email = driver.findElement(By.cssSelector("tbody tr:first-child td:nth-child(5)")).getText();

        WebElement css = driver.findElement(By.cssSelector("a.btn.btn-primary.btn-main"));
        css.click();

        driver.findElement(By.cssSelector("div.container.mt-3 form > div:nth-child(4) div:nth-child(1) .form-control")).sendKeys("AutCreated");
        driver.findElement(By.cssSelector("div.container.mt-3 form > div:nth-child(4) div:nth-child(2) .form-control")).sendKeys("UserSurname");
        driver.findElement(By.cssSelector("div.container.mt-3 form > div:nth-child(5) div:nth-child(1) .form-control")).sendKeys(email);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(driver.findElement(By.cssSelector(".app-header div.flash div.alert.col-12.fade.show.col-md-8.col-xl-6.col-xxl-4.alert-danger")).isDisplayed()){
            System.out.println("Provided email already exists");
        }else{
            System.out.println("Client got created with an already existing email");
        }
    }

    @Test(priority=3)
    public void deleteClientTest(){

        WebDriverWait wait3 = new WebDriverWait(driver, 3);

        navigator.navigateToClients();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));

        int rowCount3 = 0;

        if(driver.findElement(By.cssSelector(".app-table")).isDisplayed()){
            rowCount3=driver.findElements(By.cssSelector(".table.table-hover tbody tr")).size();
        }else{
            System.out.println(("Element not present, table empty"));
        }

        driver.findElement(By.cssSelector("tbody tr:first-child div.custom-control.custom-checkbox")).click();
        driver.findElement(By.cssSelector("div.navbar-nav.ml-auto a:nth-child(6)")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(User.password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int rowCount4=driver.findElements(By.cssSelector(".table.table-hover tbody tr")).size();

        assertTrue("Client not on the table", rowCount3 > rowCount4);

        if(driver.findElement(By.cssSelector(".app-header div.flash div.alert.col-12.fade.show.col-md-8.col-xl-6.col-xxl-4.alert-success")).isDisplayed()){
            System.out.println("Client Removed Successfully");
        }else {
            System.out.println(("Client not removed correctly"));
        }
    }
}
