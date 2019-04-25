package AppEnv.Helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitClasses {

    public void waitForUrlContains(String expectedString, WebDriver driver, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
        ExpectedCondition<Boolean> urlIsCorrect = arg0 ->    driver.getCurrentUrl().contains(expectedString);
        wait.until(urlIsCorrect);
    }

    //TODO: redo waitForLoad
//    public void waitForLoad(WebDriver driver) {
//        ExpectedCondition<Boolean> pageLoadCondition = new
//                ExpectedCondition<Boolean>() {
//                    public Boolean apply(WebDriver driver) {
//                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
//                    }
//                };
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(pageLoadCondition);
//    }
}
