package method;

import driver.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
public class Method extends Driver {
    public static WebElement find(By locator){
        try{
            waitVisibleElement(locator);
            return getDriver().findElement(locator);
        }catch (Exception e){
           e.printStackTrace();
            return null;
        }
    }
    public static List<WebElement> finds(By locator){
        try{
            webDriverWait(locator);
            return getDriver().findElements(locator);
        }catch (Exception e){
            e.printStackTrace();
            return null;
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

        public static void waitVisibleElement(By locator){
        WebDriverWait wait=new WebDriverWait(getDriver(),10,500);
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

    public static void jsScroll(By by){
        JavascriptExecutor js=(JavascriptExecutor)getDriver();
        js.executeScript("arguments[0].scrollIntoView()",find(by));
    }

    public static void jsClick(WebElement webElement){
        JavascriptExecutor js=(JavascriptExecutor)getDriver();
        js.executeScript("arguments[0].click()",webElement);
    }

    //单选下拉框，默认选择第一个
    public static void selectFirst(WebElement select,By selectValue){
        select.click();
        waitElementText(selectValue,find(selectValue).getText());
        jsClick(find(selectValue));
    }

    //输入结果后，下拉框显示联想的结果，并选择第一个
    public static void selectFirst(String input,WebElement select,By selectValue){
        select.sendKeys(input);
        waitElementText(selectValue,input);
        jsClick(find(selectValue));
    }

//    public static String alterMessage(){
//        webDriverWait(By.xpath("//div[@role='alert']"));
//        return find(By.xpath("//div[@role='alert']")).getText();
////        Alert alert=new WebDriverWait(getDriver(),10).until(ExpectedConditions.alertIsPresent());
////        getDriver().switchTo().alert();
////        return alert.getText();
//    }
}
