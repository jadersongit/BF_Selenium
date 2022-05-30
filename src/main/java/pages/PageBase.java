/* 
POM - Page base
All the main gestures, actions, explicit waits and methods listed to support all POMs
*/

package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
    WebDriver driver;
    
    //Constant time (seconds) to be used in the Explicit waits
    public static final long WAIT = 10;

    public PageBase(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        driver = webDriver;
    }

    //Wait for an element to be visible within 10 seconds
    public void waitForVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    //Wait for an element to be clickable within 10 seconds
    public void click(WebElement element){
        new WebDriverWait(driver, WAIT).until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    //Method to send text into forms
    public void sendText(WebElement element, String text){
        waitForVisibility(element);
        element.sendKeys(text);
    }

    //Method to hover and click on Menu and Sub-menu in sequence
    public void selectMenu(WebElement mainMenu, WebElement subMenu){
        Actions actions = new Actions(driver);
        waitForVisibility(mainMenu);
        actions.moveToElement(mainMenu).perform();
        waitForVisibility(subMenu);
        actions.moveToElement(subMenu).click().perform();
    }

    //Method to hover and click an element
    public void hoverAndClick(WebElement element){
        Actions actions = new Actions(driver);
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(element).click().perform();
    }

    //Method to scroll into an element positioning in the middle of screen
    public void scrollInto(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForVisibility(element);
        new WebDriverWait(driver, WAIT).until(ExpectedConditions.elementToBeClickable(element));
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                                            + "var elementTop = arguments[0].getBoundingClientRect().top;"
                                            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        js.executeScript(scrollElementIntoMiddle, element);
    }

    //Method to return a string with the text from an element
    public String getText(WebElement element){
        new WebDriverWait(driver, WAIT).until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    //Method to return a string with the url/link from an element
    public String getLink(WebElement element){
        waitForVisibility(element);
        return element.getAttribute("href");
    }

}
