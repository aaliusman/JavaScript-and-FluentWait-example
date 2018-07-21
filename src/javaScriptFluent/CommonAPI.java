package javaScriptFluent;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CommonAPI {

    public WebDriver handleWindow(WebDriver driver, String parent) {


        Set<String> allWindow = driver.getWindowHandles();
        int count = allWindow.size();
        System.out.println("window size: " + count);

        for (String child : allWindow) {
            if (!parent.equalsIgnoreCase(child)) {
                driver.switchTo().window(child);
                driver.get("https://www.facebook.com/");
            }
        }
        return driver;
    }

    public void openBlankTab(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
    }

    public static void clickElementByJs(WebElement element, WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].click()",element);
    }

    public static void fluentWait(WebDriver driver){

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(new Function<WebDriver, WebElement>() {
            @Override

                public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//*[@id=\"u_0_a\"]"));
            }
        });
    }
}
