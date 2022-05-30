/* 
POM - Blog Page
All the main locators and methods listed to interact with the posts at Blog - Blankfactor
*/

package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BlogPage extends PageBase{

    public BlogPage(WebDriver driver) {
        super(driver);
    }

    //List of all Post links
    @FindBy(xpath = "//div[@class='posts-list']//h2//a")
    List<WebElement> postsLink;

    //List of all Posts
    @FindBy(xpath = "//div[@class='posts-list']//article")
    List<WebElement> postsList;

    //Results info with total of posts
    @FindBy(xpath = "//div[@class='results']")
    WebElement resultsInfo;

    //Load more button (more posts are displayed)
    @FindBy(xpath = "//div[@class='load-more-btn-wrap']//button")
    WebElement loadMoreBtn;

    public void listOfPosts(){
            for(WebElement post : postsList){
                System.out.println("Post: " + post.getText());
            }
    }

    public WebElement lastPost(){
        return postsList.get(postsList.size() - 1);
    }
   
    public PostPage clickPost(WebElement post){
        click(post);
        return new PostPage(driver);
    }

    public void loadAllPosts(){
        while(true){
            scrollInto(lastPost());
            try {
                hoverAndClick(loadMoreBtn);
            } catch (Exception e) {
                break;
            }
        }
    }

    public int getPostsTotal(){
        return postsList.size();
    }

    public void listAllPosts(){
        loadAllPosts();
        int count = 0;
        for(WebElement post : postsLink){
            count++;
            System.out.println("Post # " + count + ": " + getText(post));
            System.out.println("     Link: " + getLink(post));
        }
    }

    public PostPage goToPost(String title){
        int count = 0;
        while(true){
            for(WebElement post:postsLink){
                if(post.getText().equals(title)){
                    scrollInto(post);
                    return clickPost(post);
                } else {
                    scrollInto(lastPost());
                }
            }
            try {
                hoverAndClick(loadMoreBtn);
            } catch (Exception e) {
                //No more load button
                count++;
                if (count > 1){
                    //No post found
                    break;
                }
            }
        }
        return new PostPage(driver);
    }

    public int getResultsTotal(){
        scrollInto(lastPost());
        String result = getText(resultsInfo).substring(14, 16);
        return Integer.parseInt(result);
    }

}



