import driver.Config;
import driver.Driver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.LoginPage;

public class TestLogin {

    @BeforeClass
    public static void beforeMethod(){
       Driver.start();
    }

    @Test
    public void loginSuccess(){
        Config config=Config.load("/data/config.yaml");
        Assert.assertEquals(new LoginPage().loginSuccess(config.username,config.password).getUserName(),"Admin Company");
    }

    @Test
    public void loginFail(){
        Config config=Config.load("/data/config.yaml");
        String info="Your login attempt has failed. Make sure the username and password are correct.";
        Assert.assertEquals(new LoginPage().loginFail(config.username,"123").getLoginFailMessage(),
                info);
        Assert.assertEquals(new LoginPage().loginFail("123",config.password).getLoginFailMessage(),
                info);
        Assert.assertEquals(new LoginPage().loginFail("123","123").getLoginFailMessage(),
                info);
    }
}
