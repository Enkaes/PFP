package test.RegressionCases;

import AppEnv.Helper.Navigator;
import AppEnv.Setup;
import AppEnv.User;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvisorUserTests extends Setup {

    private Navigator navigator = new Navigator();

    @Test
    public void advisorPasswordChange() {

        WebDriverWait wait3 = new WebDriverWait(driver, 3);
        String newPassword = "ChangedPass1";

        navigator.navigateToProfile();

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(User.password);
        driver.findElement(By.cssSelector("div.field-password.input-group input[type='password']")).sendKeys(newPassword);
        driver.findElement(By.cssSelector("form.settings-profile-photo-form div.form-group:nth-child(4) input[type='password']")).sendKeys(newPassword);
        driver.findElement(By.cssSelector("div.row:nth-child(5) button[type='submit']")).click();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-header div.flash div.alert.col-12.fade.show.col-md-8.col-xl-6.col-xxl-4.alert-success")));

        driver.get(baseUrl()+ "/signout");

        //Reaccess
        driver.get(Setup.baseUrl());

        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(User.name);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(newPassword);
        driver.findElement(By.cssSelector("button")).click();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.id("app-header")));

        assertEquals(Setup.baseUrl()+"dashboard", driver.getCurrentUrl());

        //Revert to default
        navigator.navigateToProfile();

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(newPassword);
        driver.findElement(By.cssSelector("div.field-password.input-group input[type='password']")).sendKeys(User.password);
        driver.findElement(By.cssSelector("form.settings-profile-photo-form div.form-group:nth-child(4) input[type='password']")).sendKeys(User.password);
        driver.findElement(By.cssSelector("div.row:nth-child(5) button[type='submit']")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(driver.findElement(By.cssSelector(".app-header div.flash div.alert.col-12.fade.show.col-md-8.col-xl-6.col-xxl-4.alert-success")).isDisplayed()){
            System.out.println("Password Changed Successfully");
        }else{
            System.out.println("Password was not changed");
        }
    }

    @Test(priority=2)
    public void addingTeam(){
        WebDriverWait wait3 = new WebDriverWait(driver, 10);

        //Count invoices
        driver.get(Setup.baseUrl()+ "settings/history");
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-table")));
        Integer invoiceCount;
        invoiceCount = driver.findElements(By.cssSelector(".app-table tr")).size();

        //Check billing for amount of Teams
        navigator.navigateToSubscription();
        String billingteam1;
        billingteam1 = driver.findElement(By.cssSelector("tbody tr:nth-child(2) td:nth-child(5)")).getText();

        //Add new Team
        navigator.navigateToTeams();
        wait3.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-primary.btn-main")));
        driver.findElement(By.cssSelector("a.btn.btn-primary.btn-main")).click();
        driver.findElement(By.cssSelector("div.app-content-drawer input[type='text']")).sendKeys("TestTeam2");
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("m.limont" + RandomString.make(3) + "@precisefp.com");
        driver.findElement(By.cssSelector("div.field-phone.input-group input")).sendKeys("3123121232");
        driver.findElement(By.cssSelector("button[type='Submit']")).click();

        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".app-header div.flash div.alert.col-12.fade.show.col-md-8.col-xl-6.col-xxl-4.alert-success"))).isDisplayed();

        //Recount invoices
        driver.get(Setup.baseUrl()+ "settings/history");
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a.btn.btn-sm.btn-secondary")));
        Integer invoiceCount2;
        invoiceCount2 = driver.findElements(By.cssSelector(".app-table tr")).size();

        if(invoiceCount2 > invoiceCount){
            System.out.println("New invoice added");
        }else{
            System.out.println("No new invoices present");
        }

        //Recheck billing for Teams
        navigator.navigateToSubscription();
        String billingteam2;
        billingteam2 = driver.findElement(By.cssSelector("tbody tr:nth-child(2) td:nth-child(5)")).getText();

        Integer billingint2 = 0;
        billingint2 = Integer.parseInt(billingteam2.substring(1));
        Integer billingint = 0;
        billingint = Integer.parseInt(billingteam1.substring(1));

        if(billingint2>billingint){
            System.out.println("Billing info updated correctly");
        }else{
            System.out.println("Billing info not updated for additional team");
        }

        System.out.println(billingint);
        System.out.println(billingint2);
        System.out.println(invoiceCount);
        System.out.println(invoiceCount2);
    }


//    @Test(priority=3)
//    public void addingUser(){
//
//        navigator.navigateToSubscription();
//
//        String billinguser1;
//        billinguser1 = driver.findElement(By.cssSelector("tbody tr td:nth-child(5)")).getText();
//
//        navigator.navigateToUsers();

        // add user

//        recheck price
//        navigator.navigateToSubscription();
//
//        String billinguser2;
//        billinguser2 = driver.findElement(By.cssSelector("tbody tr td:nth-child(5)")).getText();
//
//        if(billinguser2 > billinguser1){
//            System.out.println("Billing info updated correctly");
//        }else{
//            System.out.println("Billing info not updated for additional user");
//        }
//    }
}
