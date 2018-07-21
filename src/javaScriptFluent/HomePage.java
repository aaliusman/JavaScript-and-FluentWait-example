package javaScriptFluent;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class HomePage extends CommonAPI {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Usman\\eclipse-workspace\\Selenium_2\\drivers\\chromedriver.exe");

        WebDriver driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('lst-ib').value = 'Ripon Al Wasim';");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("selenium books");
        driver.findElement(By.name("q")).clear();
        jse.executeScript("document.getElementById('lst-ib').value = 'Selenium Books';");

        CommonAPI commonAPI = new CommonAPI();
        String parent = driver.getWindowHandle();
        commonAPI.openBlankTab(driver);
//        ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
//        System.out.println("Parent window is: "+parent);
//        driver.findElement(By.tagName("Body")).sendKeys(Keys.CONTROL + "t");
//        String selectLinkNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
//        driver.findElement(By.linkText("Documentation")).sendKeys(selectLinkNewTab);
//        driver.get("https://www.facebook.com/");
//        ((JavascriptExecutor)driver).executeScript("window.open('about:blank','_blank') ");


       commonAPI.handleWindow(driver, parent);
        driver.findElement(By.id("email")).sendKeys("aali.usman@gmail.com");

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







        clickElementByJs(driver.findElement(By.xpath("//*[@id=\"u_0_a\"]")), driver);
        driver.switchTo().window(parent);
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("Selneium");



    }
}
