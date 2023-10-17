package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.HashMap;

public class LeadPage extends Method{
    private static By searchInput=xpathOrId("//input[contains(@placeholder,'Name / Email / Phone')]");
    public static By nameLabel=xpathOrId("//a[@class='ev-link']");
    public ArrayList<String> label=new ArrayList<>();
    private static By resetBtn=xpathOrId("//i[@class='ev-icon-clear']");
    private static By searchInputBtn=xpathOrId("//i[@class='ev-icon-search']");
    public static By phoneLable=xpathOrId("//td[contains(@class,'el-table_6_column_30')]");
    public static HashMap<String,By> searchValue=new HashMap<>();
    public static By total=xpathOrId("//span[@class='el-pagination__total']");

    public void SearchInput(String input){
        find(resetBtn).click();
        waitVisibleElement(nameLabel);
        find(searchInput).sendKeys(input);
        find(searchInputBtn).click();
    }

    public ArrayList<String> getLabel(String input, By locator){
        label.clear();
        SearchInput(input);
        waitTotalUpdate(find(total),find(total).getText());
        for(WebElement webElement:finds(locator)){
            label.add(webElement.getText());
        }
        return label;
    }

    public static HashMap getSearchValue(String str1){
        searchValue.put(str1,nameLabel);
        return searchValue;
    }
}
