package page;

import method.Method;
import org.openqa.selenium.By;

public class HomePage extends Method {
    private static By userIcon=xpathOrId("//button[contains(@title,'Admin Company')]");
    private String userName;
    private static By contacts=xpathOrId("//i[@class='ev-icon ev-icon-contact-s']");
    private static By leads=xpathOrId("//i[@class='ev-icon ev-icon-lead2']");
    public String getUserName(){
        if (userName!=null){
            return userName;
        }else {
            userName=find(userIcon).getText();
            return userName;
        }
    }

    public ContactPage goToContacts(){
        find(contacts).click();
        return ContactPage.getInstance();
    }

    public LeadPage goToLeads(){
        find(leads).click();
        return LeadPage.getInstance();
    }
}
