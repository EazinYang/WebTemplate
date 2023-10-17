import driver.Config;
import driver.Driver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.ContactPage;
import page.HomePage;
import page.LoginPage;

import java.util.ArrayList;

public class TestContact {
    @BeforeClass
    public void beforeClass(){
        Config config=Config.load("/data/config.yaml");
        Driver.start();
        new LoginPage().loginSuccess(config.username,config.password);
        HomePage.goToContacts();
    }

    @AfterClass
    public void afterClass(){
        Driver.getDriver().quit();
        Driver.getThreadLocal().remove();
    }

    @Test
    public void SearchName(){
        for(Object key:ContactPage.getSearchValue("admin").keySet()){
            boolean b=false;
            ArrayList<String> list=new ContactPage().getLabel((String)key,ContactPage.searchValue.get(key));
            if(!list.isEmpty()) {
                for (String name : list) {
                    if (name.toLowerCase().contains((String) key)) {
                        b = true;
                    } else {
                        b = false;
                        break;
                    }
                }
                Assert.assertTrue(b, "搜索结果不一致！");
            }else {
                Assert.assertTrue(false,"没有搜索到结果");
            }
        }
    }
}
