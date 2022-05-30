/* 
Navigation Manager
All the main methods listed to interact with the browser, as getting the current URL
and going back 
*/

package utils;

import org.openqa.selenium.WebDriver;

public class NavigationManager {
    WebDriver driver;

    public NavigationManager(WebDriver webDriver){
        driver = webDriver;
    }

    public void goBack(){
        driver.navigate().back();
    }

    public String getURL(){
        return driver.getCurrentUrl();
    }

}
