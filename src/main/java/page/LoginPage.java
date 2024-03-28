package page;

import driver.Config;
import method.Method;
import org.openqa.selenium.By;

public class LoginPage extends Method {
    private By accountBtn=xpathOrId("//button[2]");
    private By username=xpathOrId("//input[contains(@placeholder,'Username')]");
    private By password=xpathOrId("//input[contains(@placeholder,'Password')]");
    private By loginBtn=xpathOrId("//button");
    private By loginFailMessage=xpathOrId("//div[@role='alert']");


    public CompaniesPage loginSuccess(){
        Config config=Config.load("/data/config.yaml");
        find(accountBtn).click();
        find(username).sendKeys(config.username);
        find(password).sendKeys(config.password);
        find(loginBtn).click();
        return CompaniesPage.getInstance();
    }

    public LoginPage loginFail(String account,String pwd){
        find(accountBtn).click();
        find(username).sendKeys(account);
        find(password).sendKeys(pwd);
        find(loginBtn).click();
        return new LoginPage();
    }

    public String getLoginFailMessage() {
        return find(loginFailMessage).getText();
    }
}
