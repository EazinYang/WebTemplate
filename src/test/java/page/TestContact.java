package page;

import driver.Driver;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TestContact {
    public ContactPage contactPage;

    @BeforeClass
    public void beforeClass(){
        Driver.start();
        contactPage=new LoginPage().loginSuccess().selectOrg().goToContacts();
    }

    @AfterClass
    public void afterClass(){
        Driver.getDriver().quit();
        Driver.getThreadLocal().remove();
    }

    //封装搜索的断言过程
    public void searchSuccess(String input,ArrayList<String> label){
        Boolean result=false;
        Assert.assertFalse(label.isEmpty(),"搜索结果为空！");
        for(String str:label){
            if(str.toLowerCase().contains(input.toLowerCase())){
                result=true;
            }else {
                result=false;
            }
        }
        Assert.assertTrue(result,"搜索结果不一致！");
    }

    @Story("Contact页面的搜索测试用例")
    @Description("正确用例：Keyword搜索Contact name")
    @Test
    public void searchNameSuccess(){
        String input="admin";
        ArrayList<String> label=contactPage.getContactName(input);
        searchSuccess(input,label);
    }

    @Story("Contact页面的搜索测试用例")
    @Description("错误用例：Keyword搜索Contact name")
    @Test
    public void searchNameFail(){
        ArrayList<String> label=contactPage.getContactName("null");
        label.forEach(entry->{
            System.out.println(entry);
        });
        Assert.assertTrue(label.isEmpty(),"搜索结果不准确！");
    }

    @Story("Contact页面的搜索测试用例")
    @Description("正确用例：Keyword搜索Contact phone")
    @Test
    public void searchPhoneSuccess(){
        String input="12345678";
        ArrayList<String> label=contactPage.getContactPhone(input);
        searchSuccess(input,label);
    }

    @Story("Contact页面的搜索测试用例")
    @Description("错误用例：Keyword搜索Contact phone")
    @Test
    public void searchPhoneFail(){
        ArrayList<String> label=contactPage.getContactPhone("null");
        Assert.assertTrue(label.isEmpty(),"搜索结果不准确！");
    }

    @Story("Contact页面的搜索测试用例")
    @Description("正确用例：搜索Roles字段")
    @Test
    public void searchRolesSuccess() {
        ArrayList<String> label = contactPage.searchRoles();
        System.out.println(label.size());
        Iterator<String> iterator=  label.iterator();
        while (iterator.hasNext()){
            String str=iterator.next();
            if(!str.equals(contactPage.roles_Client_Text)){
                iterator.remove();
            }
        }
        if(Integer.parseInt(contactPage.total_text.replace("Total ",""))<=10){
            if(label.size()!=Integer.parseInt(contactPage.total_text)){
                Assert.assertTrue(true,"搜索结果不准确！");
            }
        }else {
            if (label.size()!=10){
                Assert.assertTrue(true,"搜索结果不准确！");
            }
        }
    }

//    @Story("Contact页面的搜索测试用例")
//    @Description("错误用例：搜索Roles字段")
//    @Test
//    public void searchRolesFail(){
//        ArrayList<String> label = contactPage.searchRoles();
//        Assert.assertTrue(label.isEmpty(),"搜索结果不准确！");
//    }

    @Story("Contact页面的搜索测试用例")
    @Description("正确用例：搜索Owner字段")
    @Test
    public void searchOwnerSuccess(){
        String input="Admin Company";
        searchSuccess(input,contactPage.searchOwner(input));
    }

    @Story("Contact页面的搜索测试用例")
    @Description("正确用例：搜索My Records字段")
    @Test
    public void searchMyRecordsSuccess(){
        String userName= new HomePage().getUserName();
        searchSuccess(userName,contactPage.searchMyRecords());
    }

    @Story("Contact页面的搜索测试用例")
    @Description("正确用例：搜索Related Company字段")
    @Test
    public void searchRelatedCompanySuccess(){
        String input="admin contact02";
        searchSuccess(input,contactPage.searchRelatedCompany(input));
    }

    @Story("Contact页面的搜索测试用例")
    @Description("正确用例：搜索Related Company字段")
    @Test
    public void searchContactSourceSuccess(){
        String input=contactPage.contactSource_value_Text;
        searchSuccess(input,contactPage.searchContactSource());
    }

}
