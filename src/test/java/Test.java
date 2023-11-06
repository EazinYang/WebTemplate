import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Random;

public class Test {
    @org.testng.annotations.Test
    public void test1(){
        WebDriver driver=new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.manage().window().maximize();
//        WebElement w= driver.findElement(By.id("my-check-1"));
//        if(w.isSelected()){
//            w.click();
//        }
        WebElement dis=driver.findElement(By.name("my-disabled"));
        System.out.println(dis.isEnabled() ? "启用状态":"禁用状态");
        System.out.println(driver.findElement(By.id("my-check-1")).getRect().getX());
        System.out.println(driver.findElement(By.id("my-check-1")).getRect().getY());
        System.out.println(driver.findElement(By.id("my-check-1")).getRect().getHeight());
        System.out.println(driver.findElement(By.id("my-check-1")).getRect().getDimension());
        driver.findElement(By.name("my-file")).sendKeys("D:/360安全浏览器下载/List1.txt");
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",driver.findElement(By.id("my-check-1")));
//        WebDriverWait wait=new WebDriverWait(driver,5,500);
//        Alert alert=wait.until(ExpectedConditions.alertIsPresent());
//        alert=driver.switchTo().alert();
//        alert.accept();
//        alert.dismiss();
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles().size());
        js.executeScript("window.open('https://www.selenium.dev/selenium/web/web-form.html')");
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles().size());
        driver.close();    }
}
