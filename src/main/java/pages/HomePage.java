/* 
POM - Home Page
All the main locators and methods listed to interact with the Blankfactor landing page, as intereacting with the Menu
and accepting cookies.
*/

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase{

    public HomePage(WebDriver driver){
        super(driver);
    }

    //Accept cookies button
    @FindBy(id = "hs-eu-confirmation-button")
    WebElement acceptCookiesBtn;

    //Insights main menu with sub-menu items such as: Blog, White papers, Webinars, News
    @FindBy(id = "menu-item-4436")
    WebElement insightsMenu;
    
    //Blog sub-menu item
    @FindBy(xpath = "//p[text()=' Blog']")
    WebElement blogSubMenu;

    public void clickAcceptCookies(){
        click(acceptCookiesBtn);
    }
    
    public BlogPage clickBlogMenu(){
        selectMenu(insightsMenu, blogSubMenu);
        return new BlogPage(driver);
    }

}
