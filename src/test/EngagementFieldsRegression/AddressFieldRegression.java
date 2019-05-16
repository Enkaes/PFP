package test.EngagementFieldsRegression;

import AppEnv.Helper.EngagementSender;
import AppEnv.Helper.Navigator;
import AppEnv.Setup;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class AddressFieldRegression extends Setup {

    private EngagementSender sender = new EngagementSender();

    private String newAddressValue1 = RandomString.make(4);
    private String newAddressValue2 = RandomString.make(4);
    private String newAddressValue3 = RandomString.make(4);

    @Test(priority = 1)
    public void addressSaveToProfile(){

        WebDriverWait wait3 = new WebDriverWait(driver, 5);

        sender.sendEngagement();

        String profileUrl = driver.getCurrentUrl();

        String addressValueLine1 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-12 input.form-control")).getAttribute("value");
        String addressValueLine2 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12 input.form-control")).getAttribute("value");
        String addressValueLine3 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12:nth-child(6) input.form-control")).getAttribute("value");

        sender.accessEngagement();

        driver.findElement(By.cssSelector("div.form-row.address-row div.col-12 input.form-control")).sendKeys(newAddressValue1);
        driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12 input.form-control")).sendKeys(newAddressValue2);
        driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12:nth-child(6) input.form-control")).sendKeys(newAddressValue3);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.get(profileUrl);
        wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.calculated.form-group")));

//        sender.switchTab();

        String updatedLine1 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-12 input.form-control")).getAttribute("value");
        String updatedLine2 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12 input.form-control")).getAttribute("value");
        String updatedLine3 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12:nth-child(6) input.form-control")).getAttribute("value");

        if (updatedLine1 != addressValueLine1 && updatedLine2 != addressValueLine2 && updatedLine3 != addressValueLine3){
            System.out.println("All fields updated correctly");
        }else {
            if (updatedLine1 != addressValueLine1 ){
                System.out.println("Address line 1 updated correctly");
            } else {
                System.out.println("Address line 1 was not updated");
            }
            if (updatedLine2 != addressValueLine2 ){
                System.out.println("City updated correctly");
            } else {
                System.out.println("City was not updated");
            }
            if (updatedLine3 != addressValueLine3 ){
                System.out.println("Zip updated correctly");
            } else {
                System.out.println("Zip was not updated");
            }
        }
    }

    @Test(priority=2)
    public void addressSaveTrigger(){

        WebDriverWait wait3 = new WebDriverWait(driver, 5);

        //create a client

        sender.sendEngagement();

        String profileUrl = driver.getCurrentUrl();

        //clear input
        driver.findElement(By.cssSelector("div.form-row.address-row div.col-12 input.form-control")).clear();

        String addressValueLine1 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-12 input.form-control")).getAttribute("value");
        String addressValueLine2 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12 input.form-control")).getAttribute("value");
        String addressValueLine3 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12:nth-child(6) input.form-control")).getAttribute("value");

        sender.accessEngagement();

        // Send input to address fields one by one, check if save gets triggered
        driver.findElement(By.cssSelector("div.form-row.address-row div.col-12 input.form-control")).sendKeys(newAddressValue1);
        Assert.assertFalse( "Save triggered on first line" ,driver.findElement(By.cssSelector("div.progress.active")).isDisplayed());

        driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12 input.form-control")).sendKeys(newAddressValue2);
        Assert.assertFalse( "Save triggered on second line" ,driver.findElement(By.cssSelector("div.progress.active")).isDisplayed());

        driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12:nth-child(6) input.form-control")).sendKeys(newAddressValue3);
        if(driver.findElement(By.cssSelector("div.progress.active")).isDisplayed()) {
            driver.get(profileUrl);
            wait3.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.calculated.form-group")));
        }else {
            System.out.println("Address field changes have not triggered Save");
        }

        String updatedLine1 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-12 input.form-control")).getAttribute("value");
        String updatedLine2 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12 input.form-control")).getAttribute("value");
        String updatedLine3 = driver.findElement(By.cssSelector("div.form-row.address-row div.col-md.col-sm-6.col-xs-12:nth-child(6) input.form-control")).getAttribute("value");

        }
    }
    //dataset change of lines?
