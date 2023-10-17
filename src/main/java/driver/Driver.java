package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class Driver {
    protected static ThreadLocal<WebDriver> threadDriver=new ThreadLocal<WebDriver>();

    public static void start(){
        WebDriver driver=new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        Config config=Config.load("/data/config.yaml");
        threadDriver.set(driver);
        getDriver().get(config.url);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver(){
        return threadDriver.get();
    }

    public static ThreadLocal getThreadLocal(){
        return threadDriver;
    }
}
