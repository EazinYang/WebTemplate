package page;

import driver.Config;
import driver.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LoginPage;

public class TestLogin {

    @BeforeClass
    public static void beforceClass(){
       Driver.start();
    }

    @AfterClass
    public static void afterClass(){
        Driver.getDriver().quit();
        Driver.getThreadLocal().remove();
    }

    @Test
    public void loginSuccess(){
        Assert.assertEquals(new LoginPage().loginSuccess().getUserName(),"Admin User");
    }

    @DataProvider(name = "loginFailParameter")
    public String[][] loginFailParameter(){
        Config config=Config.load("/data/config.yaml");
        String[][] parameter={
                {config.username,"123"},
                {"123",config.password},
                {"123","123"}
        };
        return parameter;
    }

    @Test(dataProvider = "loginFailParameter")
    public void loginFail(String username,String password){
        String info="Your login attempt has failed. Make sure the username and password are correct.";
        Assert.assertEquals(new LoginPage().loginFail(username,password).getLoginFailMessage(),
                info);
    }


}
