/* 
Scenario:
    1. Navigate to “https://blankfactor.com/”;
    2. Open “Blog” section;
    3. Scroll down to - “Why Fintech in Latin America Is Having a Boom” and click the post by Sofia Gonzalez;
    4. Make validation (that the script is on the correct page, by verifying url, some of the text on the page);
    5. Subscribe to the newsletter using the subscribe form; 
    6. Go back to the Blog section and print a list of all posts titles with related links. 

For the scenario, there are 3 tests:
    1. Open Post Test (Assert the right post is opened);
    2. Subscribe to Newsletter Test (Assert the email was submitted properly);
    3. Print All Posts and Links Test (Assert all the posts and links were printed accordly matching the total displayed).
*/

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PostPage;
import pages.BlogPage;

public class BlankfactorTest extends TestBase{
    PostPage postPage;
    BlogPage blogPage;

    //All the tests will keep running even with a fail
    SoftAssert softAssert = new SoftAssert();
    
    
    @Test (priority = 1)
    public void open_post_test(){
        String title = "Why Fintech in Latin America Is Having a Boom";
        String postUrl = "https://blankfactor.com/insights/blog/fintech-in-latin-america/";
        homePage.clickAcceptCookies();
        blogPage = homePage.clickBlogMenu();
        postPage = blogPage.goToPost(title);
        String url = navigation().getURL();
        softAssert.assertEquals(postPage.getPostTitle(), title, "Posts title do not match");
        softAssert.assertEquals(url, postUrl, "Post and link do not match");
        softAssert.assertAll();
    }

    @Test (priority = 2)
    public void subscribe_to_newsletter_test(){
        String email = "blankfactor" + System.currentTimeMillis() + "@mailinator.com";
        postPage.enterEmail(email);
        postPage.submitEmail();
        softAssert.assertEquals(postPage.getConfirmationMsg(), "Thank you for subscribing! Stay tuned.", "Failed to subscribe");
        softAssert.assertAll();
    }

    @Test (priority = 3)
    public void print_all_posts_and_links_test(){
        navigation().goBack();
        String url = navigation().getURL();
        softAssert.assertEquals(url, "https://blankfactor.com/insights/blog/", "Blog URL do not match");
        blogPage.listAllPosts();
        int postsTotal = blogPage.getPostsTotal();
        int resultsTotal = blogPage.getResultsTotal();
        softAssert.assertEquals(postsTotal, resultsTotal, "Results do not match");
        softAssert.assertAll();
    }
}
