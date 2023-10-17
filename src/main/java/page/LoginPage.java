package page;

import org.openqa.selenium.By;

public class LoginPage extends Method{
    private By accountBtn=xpathOrId("//button[2]");
    private By username=xpathOrId("//input[contains(@placeholder,'Username')]");
    private By password=xpathOrId("//input[contains(@placeholder,'Password')]");
    private By loginBtn=xpathOrId("//button");
    private By loginFailMessage=xpathOrId("//div[@role='alert']");


    public HomePage loginSuccess(String account,String pwd){
        find(accountBtn).click();
        find(username).sendKeys(account);
        find(password).sendKeys(pwd);
        find(loginBtn).click();
        return new HomePage();
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
