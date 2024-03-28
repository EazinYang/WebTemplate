package page;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import method.Method;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ContactPage extends Method {
    /**
     * 搜索界面的元素
     */
    private static By searchInput=xpathOrId("//input[contains(@placeholder,'Name / Email / Phone / Agent Id')]");
    public static By nameLabel=xpathOrId("//a[@class='ev-link']");//contact name label
    public static By phoneLabel=xpathOrId("//tr[@class='el-table__row']/td[4]/div");//phone label
    public ArrayList<String> label=new ArrayList<>();//存储Contact Name label
    private static By resetBtn=xpathOrId("//i[@class='ev-icon-clear']");//列表页面重置按钮
    private static By searchInputBtn=xpathOrId("//i[@class='ev-icon-search']");//Keywrd搜索框的搜索按钮
    public static By total=xpathOrId("//span[@class='el-pagination__total']");//列表页面的total 字段
    public String total_text="";//total字段的文本
    public static By roles_Client=xpathOrId("//div[@valuesfield='Contact_Roles__c']/div/label[1]");//Roles条件的Client字段//input[@value='Client']/../span
    public static By roleLabel=xpathOrId("//tr[@class='el-table__row']/td[3]/div/div");//Roles label
    public String roles_Client_Text="";
    public static By searchOwnerInput=xpathOrId("//input[contains(@placeholder,'Search owner')]");//搜索条件的Owner字段
    public static By ownerLabel=xpathOrId("//tr[@class='el-table__row']/td[7]/div");//列表页面的Ownerlabel
    public static By searchOwnerInputresult=xpathOrId("/html/body/div[last()]/div/div/ul/li/div");//Onwer输入后联想结果的第一个
    public static By searchRatingInput=xpathOrId("//div[@valuesfield='Rating']/div/div");//搜索条件的Rating字段
    public static By searchMyRecordsRadio=xpathOrId("//div[@originali18n='search.contacts.condition.myRecords']/div/div");//搜索条件的My Records字段
    public static By searchRelatedCompanyInput=xpathOrId("//input[contains(@placeholder,'Search related company')]");//搜索条件的Related Company字段
    public static By relatedCompanyLabel=xpathOrId("//tr[@class='el-table__row']/td[6]/div");//列表页面的Related Company label
    public static By searchContactSourceSelect=xpathOrId("//div[@valuesfield='Contact_Source__c']/div/div");//搜索条件Contact Source字段
    public static By contactSourceLabel=xpathOrId("//tr[@class='el-table__row']/td[5]/div");//列表页面的Contact Source label
    public static By searchContactSourceResult=xpathOrId("/html/body/div[last()]/div[1]/div/ul/li[1]");//Contact Source下拉框的第一个选项
    public String contactSource_value_Text="";
    /**
     * 添加Contact的元素
     */
    public static By addContactBtn=xpathOrId("//*[@class=\"app__header\"]/div/div[5]/div/button");//add contact按钮
    public static By roleField=xpathOrId("//input[@tabindex='-1'][@type='text']");//Contact表单Role字段
    public static By ratingField=xpathOrId("//div[@valuesfield='Rating']/div/div/input");//Contact表单Rating字段
    public static By contactSourceField=xpathOrId("//div[@valuesfield='Contact_Source__c']/div/div/input");//Contact表单ContactSource字段
    public static By salutationField=xpathOrId("//div[@valuesfield='Salutation']/div/div/input");//Contact表单的salutation字段
    public static By firstNameField=xpathOrId("//label[@for='FirstName']/../div/div/div/input");//Contact表单的First Name字段
    public static By lastNameField=xpathOrId("//label[@for='LastName']/../div/div/div/input");//Contact表单的Last Name字段
    public static By localNameField=xpathOrId("//label[@for='Local_Name__c']/../div/div/div/input");//Contact表单的Local Name字段
    public static By genderField_Female=xpathOrId("//input[@type='radio'][@value='Female']");//Contact表单的Gender字段的Female选项
    public static By companyNameField=xpathOrId("//label[@for='Company_Name__c']/../div/div/div/input");//Contact表单的Company Name字段
    public static By companySearchReferenceNumberField=xpathOrId("//label[@for='Company_Search_Ref_No__c']/../div/div/div/input");//Contact表单的Company Search Reference Number字段
    public static By companySearchDateField=By.cssSelector("[refwrapname='refWrapCompany_Search_Date__c']>div>div>input");//Contact表单的Company Search Date字段
    public static By companyRegistrationNumberField=xpathOrId("//label[@for='Company_Registration_Number__c']/../div/div/div/input");//Contact表单的Company Registration Number字段
    public static By licenseTypeField=xpathOrId("//label[@for='License_Type__c']/../div/div/div/input");//Contact表单的License Type字段
    public static By eaLicenceField=xpathOrId("//label[@for='Agent_ID__c']/../div/div/div/input");//Contact表单的EA Licence字段
    public static By mediumField=xpathOrId("//label[@for='Medium__c']/../div/div/div/div/input");//Contact表单的Medium字段
    public static By campaignField=xpathOrId("//label[@for='Campaign__c']/../div/div/div/input");//Contact表单的Campaign字段
    public static By phone1Field=xpathOrId("//label[@for='Phone']/../div/div/div/input");//Contact表单的Phone1字段
    public static By phone2Field=xpathOrId("//label[@for='Add_Phone_1__c']/../div/div/div/input");//Contact表单的Phone2字段
    public static By phone3Field=xpathOrId("//label[@for='Add_Phone_2__c']/../div/div/div/input");//Contact表单的Phone3字段
    public static By phone4Field=xpathOrId("//label[@for='Add_Phone_3__c']/../div/div/div/input");//Contact表单的Phone4字段
    public static By email1Field=xpathOrId("//label[@for='Email']/../div/div/div/input");//Contact表单的Email1字段
    public static By email2Field=xpathOrId("//label[@for='Add_Email_1__c']/../div/div/div/input");//Contact表单的Email2字段
    public static By weChatIDField=xpathOrId("//label[@for='WeChat_ID__c']/../div/div/div/input");//Contact表单的WeChat ID字段
    public static By faxField=xpathOrId("//label[@for='Fax']/../div/div/div/input");//Contact表单的Fax字段
    public static By titleField=xpathOrId("//label[@for='Title']/../div/div/div/input");//Contact表单的Title字段
    public static By locationField=xpathOrId("//label[@for='Location__c']/../div/div/div/input");//Contact表单的Location字段

    private static ContactPage contactPage;

    public static ContactPage getInstance(){
        if (contactPage==null){
            contactPage=new ContactPage();
            return contactPage;
        }else {
            return contactPage;
        }
    }

    //封装搜索前的操作
    public void searchBefore(By by){
        find(resetBtn).click();
        waitVisibleElement(nameLabel);
        waitVisibleElement(total);
        WebDriverWait wait=new WebDriverWait(getDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(xpathOrId("//li[@class='number']")));
        jsScroll(by);
        total_text=find(total).getText();
    }

    //返回搜索结果
    public ArrayList<String> searchResult(By by){
        label.clear();
        WebDriverWait wait=new WebDriverWait(getDriver(),10);
        wait.until(ExpectedConditions.elementToBeClickable(xpathOrId("//i[@class='el-tag__close el-icon-close']")));
        waitTotalUpdate(find(total),total_text);
        total_text=find(total).getText();
        waitVisibleElement(total);
        if (total_text.contains("Total 0")){
            return label;
        }
        for(WebElement webElement:finds(by)){
            label.add(webElement.getText());
        }
        return label;
    }

    //搜索用例：Name输入框的输入流程
    @Step("验证参数：input {0}")
    public ArrayList<String> searchKeyword(String input,By by){
        searchBefore(searchInput);
        find(searchInput).sendKeys(input);
        find(searchInputBtn).click();
        return searchResult(by);
    }

    //通过Name搜索框，搜索Contact Name
    public ArrayList<String> getContactName(String input){
        return searchKeyword(input,nameLabel);
    }

    //通过Name搜索框，搜索Phone返回的结果
    public ArrayList<String> getContactPhone(String input){
        return searchKeyword(input,phoneLabel);
    }

    //搜索用例：Roles字段筛选
    public ArrayList<String> searchRoles(){
        searchBefore(roles_Client);
        find(roles_Client).click();
        try{
            roles_Client_Text=find(roles_Client).getText();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return searchResult(roleLabel);
    }

    //搜索用例：Owner字段筛选
    public ArrayList<String> searchOwner(String input){
        searchBefore(searchOwnerInput);
        jsScroll(searchOwnerInput);
        selectFirst(input,find(searchOwnerInput),searchOwnerInputresult);
        jsScroll(ownerLabel);
        return searchResult(ownerLabel);
    }

    //搜索用例：My Records字段筛选
    public ArrayList<String> searchMyRecords(){
        searchBefore(searchMyRecordsRadio);
        find(searchMyRecordsRadio).click();
        return searchResult(ownerLabel);
    }

    //搜索用例：Related Company字段筛选
    public ArrayList<String> searchRelatedCompany(String input){
        searchBefore(searchRelatedCompanyInput);
        selectFirst(input,find(searchRelatedCompanyInput),searchOwnerInputresult);
        return searchResult(relatedCompanyLabel);
    }

    //搜索用例：Contact Source字段
    public ArrayList<String> searchContactSource(){
        searchBefore(searchContactSourceSelect);
        selectFirst(find(searchContactSourceSelect),searchContactSourceResult);
        contactSource_value_Text=find(searchContactSourceResult).getText();
        return searchResult(contactSourceLabel);
    }

    //添加Contact
    public void addContact(){
        find(addContactBtn).click();
        JavascriptExecutor js=(JavascriptExecutor)getDriver();
        WebElement role=find(roleField);
        //点击role下拉框，并选中所有选项
        js.executeScript("arguments[0].click()",role);
        for(int i=0;i<9;i++){
            role.sendKeys(Keys.ARROW_DOWN);
            role.sendKeys(Keys.ENTER);
        }
//        System.out.println(find(xpathOrId("//header[@class='app__header']/div/div[4]")).getRect().getDimension());
//        System.out.println(find(By.xpath("//label[@for='Contact_Roles__c']")).getRect().getDimension());
        //鼠标移动到对应坐标并点击，用于关闭下拉框
        Actions actions=new Actions(getDriver());
        //鼠标移动至"CREATE CONTACT"坐标处，并点击，用于关闭下拉框
        actions.moveByOffset(247,60).click().perform();
        actions.release();
        //点击rating下拉框，并选中第一个选项
//        selectFirst(find(ratingField));
        //点击ContactSource下拉框，并选中第一个选项
//        selectFirst(find(contactSourceField));
        //点击Salutation下拉框，并选中第一个选项
//        selectFirst(find(salutationField));
        Faker faker=new Faker();
        //输入FirstName
        find(firstNameField).sendKeys(faker.name().firstName());
        //输入LastName
        find(lastNameField).sendKeys(faker.name().lastName());
        //输入LocalName
        find(localNameField).sendKeys(faker.name().name());
        //移动页面到Local Name
        js.executeScript("arguments[0].scrollIntoView()",find(localNameField));
        //选中Gender的Female
        //输入CompanyName
        find(companyNameField).sendKeys(faker.company().name());
        //输入Company Search Reference Number
        find(companySearchReferenceNumberField).sendKeys("Company Search Reference Number");
        //Company Search Date选择日期
        LocalDate localDate=LocalDate.now();
        String date=DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate);
        js.executeScript("document.querySelector(\"[refwrapname='refWrapCompany_Search_Date__c']>div>div>input\").setAttribute('value','"+date+"');");
        //输入Company Registration Number
        find(companyRegistrationNumberField).sendKeys("Company Registration Number");
        //输入License Type
        find(licenseTypeField).sendKeys("License Type");
        //输入EA Licence
        find(eaLicenceField).sendKeys("EA Licence");
        //Medium下拉框选择第一个选项
//        selectFirst(find(mediumField));
        //输入Campaign
        find(campaignField).sendKeys("Campaign");
        //输入phone1
        find(phone1Field).sendKeys(faker.phoneNumber().cellPhone());
        //输入phone2
        find(phone2Field).sendKeys(faker.phoneNumber().cellPhone());
        //输入phone3
        find(phone3Field).sendKeys(faker.phoneNumber().cellPhone());
        WebElement phone4=find(phone4Field);
        js.executeScript("arguments[0].scrollIntoView()",phone4);
        //输入phone4
        phone4.sendKeys(faker.phoneNumber().cellPhone());
        //输入Email 1
        find(email1Field).sendKeys(faker.internet().emailAddress());
        //输入Email 2
        find(email2Field).sendKeys(faker.internet().emailAddress());
        //输入WeChat ID
        find(weChatIDField).sendKeys("WeChat ID");
        //输入Fax
        find(faxField).sendKeys("Fax");
        //输入Title
        find(titleField).sendKeys("Title");
        //Location下拉框选择第一个选项
        WebElement location=find(locationField);
        location.click();
        location.sendKeys(Keys.ARROW_DOWN);
        location.sendKeys(Keys.ARROW_DOWN);
        location.sendKeys(Keys.ARROW_DOWN);
        location.sendKeys(Keys.ENTER);
    }

}
