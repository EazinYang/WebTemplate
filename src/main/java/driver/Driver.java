package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
public class Driver {
    protected static ThreadLocal<WebDriver> threadLocal=new ThreadLocal<WebDriver>();

    public static void start(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//        ChromeOptions options=new ChromeOptions();
//        options.addArguments("blink-settings=imagesEnabled=false");
        Config config=Config.load("/data/config.yaml");
        WebDriver driver=new ChromeDriver();
        threadLocal.set(driver);
        getDriver().get(config.url);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver(){
        return threadLocal.get();
    }

    public static ThreadLocal getThreadLocal(){
        return threadLocal;
    }
}
