/* 
POM - Post Page
All the main locators and methods listed to interact with the post and "subscribe to newsletter" form
opened previously on Blog page - Blankfactor
*/

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends PageBase{

    public PostPage(WebDriver driver) {
        super(driver);
    }

    //Post main title
    @FindBy(xpath = "//h1[@class='heading-3 post-title']")
    WebElement postTitle;

    //Email field on "subscribe to newsletter" form
    @FindBy(name = "Email")
    WebElement emailField;

    //Submit button on "subscribe to newsletter" form
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitBtn;

    //Confirmation message for a successful submit on newsletter form
    @FindBy(xpath = "//div[@class='mc4wp-response']")
    WebElement confirmationMsg;

    //Error message for a unsuccessful submit on newsletter form
    @FindBy(xpath = "//div[@class='error-summary']//p")
    WebElement errorMsg;

    public String getPostTitle(){
        return getText(postTitle);
    }

    public void enterEmail(String email){
        scrollInto(emailField);
        sendText(emailField, email);
    }

    public void submitEmail(){
        click(submitBtn);
    }

    public String getConfirmationMsg(){
        try {
            return getText(errorMsg);
        } catch (Exception e) {
            return getText(confirmationMsg);
        }
    }


}
