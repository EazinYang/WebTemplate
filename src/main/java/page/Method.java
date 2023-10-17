package page;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class Method extends Driver {
    public static WebElement find(By locator){
        try{
            webDriverWait(locator);
            return getDriver().findElement(locator);
        }catch (Exception e){
            webDriverWait(locator);
            return getDriver().findElement(locator);
        }
    }

    public static By xpathOrId(String str){
        if (str.matches("/.*")){
            return By.xpath(str);
        }else {
            return By.id(str);
        }
    }

    public static By xpathFindText(String str){
        return By.xpath("//*[@text='"+str+"']");
    }

    public static void webDriverWait(By locator){
        WebDriverWait wait=new WebDriverWait(getDriver(),10);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static List<WebElement> finds(By locator){
        try{
            webDriverWait(locator);
            return getDriver().findElements(locator);
        }catch (Exception e){
            e.printStackTrace();
            webDriverWait(locator);
            return getDriver().findElements(locator);
        }
    }

    public static void waitVisibleElement(By locator){
        WebDriverWait wait=new WebDriverWait(getDriver(),10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitElementText(By locator,String text){
        WebDriverWait wait=new WebDriverWait(getDriver(),10);
        wait.until(ExpectedConditions.textToBePresentInElement(find(locator),text));
    }

    public static ExpectedCondition<Boolean> waitIgnoreElementText(final WebElement element, final String text) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = element.getText();
                    return elementText.toLowerCase().contains(text.toLowerCase());
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("text ('%s') to be present in element %s", text, element);
            }
        };
    }

    public static void waitTotalUpdate(final WebElement element, final String text){
        WebDriverWait wait=new WebDriverWait(getDriver(),10);
        wait.until(totalUpdate(element,text));
    }


    public static ExpectedCondition<Boolean> totalUpdate(final WebElement element, final String text) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    String elementText = element.getText();
                    return !elementText.equals(text);
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return String.format("text ('%s') to be present in element %s", text, element);
            }
        };
    }

//    public static String alterMessage(){
//        webDriverWait(By.xpath("//div[@role='alert']"));
//        return find(By.xpath("//div[@role='alert']")).getText();
////        Alert alert=new WebDriverWait(getDriver(),10).until(ExpectedConditions.alertIsPresent());
////        getDriver().switchTo().alert();
////        return alert.getText();
//    }
}
