package page;

import method.Method;
import org.openqa.selenium.By;

public class CompaniesPage extends Method {
    private static By userIcon=xpathOrId("//button[contains(@title,'Admin User')]");
    private String userName;

    private static By orgBtn=xpathOrId("//td[contains(@class,'el-table_1_column_5  ')]/div/button");//选择org的按钮

    private static CompaniesPage companiesPage;

    public static CompaniesPage getInstance(){
        if (companiesPage==null){
            companiesPage=new CompaniesPage();
            return companiesPage;
        }else {
            return companiesPage;
        }
    }

    public String getUserName(){
        if (userName!=null){
            return userName;
        }else {
            userName=find(userIcon).getText();
            return userName;
        }
    }

    public HomePage selectOrg(){
        find(orgBtn).click();
        return new HomePage();
    }
}
