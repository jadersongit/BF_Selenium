/* 
Test Base
All the main methods listed to interact with the WebDriver from Selenium, as setting up 
and tearing down the driver, initializing Navigation Manager and recording screenshots for Fail results
*/

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import com.google.common.io.Files;

import pages.HomePage;
import utils.NavigationManager;

public class TestBase {
    WebDriver driver;
    HomePage homePage;

    //Initialize WebDriver, opens the browser, go to the landing page and maxmize the window before all the tests
    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://blankfactor.com/");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    //Tear down the WebDriver, closes the browser after all tests are done
    @AfterClass
    public void tearDown(){
        if (null != driver){
            driver.quit();
        }
    }

    public NavigationManager navigation(){
        return new NavigationManager(driver);
    }

    //Method to get screenshots from each Fail/Pass result
    @AfterMethod
    public void recordResults(ITestResult result){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        TakesScreenshot camera = (TakesScreenshot)driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        if(ITestResult.FAILURE == result.getStatus()){
            try {
                Files.move(screenshot, new File("resources/screenshots/Fail_"+result.getName()+"_"+formatter.format(date)+".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(ITestResult.SUCCESS == result.getStatus()){
            try {
                Files.move(screenshot, new File("resources/screenshots/Pass_"+result.getName()+"_"+formatter.format(date)+".png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
