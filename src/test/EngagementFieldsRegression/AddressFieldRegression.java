package test.EngagementFieldsRegression;

import AppEnv.Helper.EngagementSender;
import AppEnv.Helper.Navigator;
import AppEnv.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AddressFieldRegression extends Setup {

    private Navigator navigator = new Navigator();
    private EngagementSender sender = new EngagementSender();

    @Test(priority = 1)
    public void addressSaveToProfile(){

        WebDriverWait wait3 = new WebDriverWait(driver, 5);

        sender.sendEngagement();

        String addressValue1 = driver.findElement(By.cssSelector("main address")).getText();
        System.out.println(addressValue1);

        sender.accessEngagement();


        /*
           fill in the field
           save and finish?
           re-access profile
           check if value got updated
           print
        */

    }
    @Test(priority=2)
    public void addressSaveTrigger(){
    }
}
