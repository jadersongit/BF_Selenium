# Test Automation - Blankfactor
This is a Selenium framework using TestNG and Maven, made to run automated tests as requested by Blankfactor:
1. Navigate to “https://blankfactor.com/”
2. Open “Blog” section
3. Scroll down to - “Why Fintech in Latin America Is Having a Boom” and click the post by Sofia Gonzalez
4. Make validation (that the script is on the correct page, by verifying url, some of the text on the page)
5. Subscribe to the newsletter using the subscribe form 
6. Go back to the Blog section and print a list of all posts titles with related links.


## Supported platforms
Selenium supports a variety of platforms, like Chrome, Firefox, Edge, others. For this framework in particular, the tests will run on **Chrome** using **ChromeDriver**.


## Requirements
Chrome updated with **v.102.0.X.X** or so, matching [ChromeDriver](https://chromedriver.chromium.org/downloads) found here on /resources/


## Framework Components
Below the components and details from the framewrok:

### *Build Tool*
**Maven**: management of project dependencies. Tests can run using *pom.xml* or *testngl.xml*.
Dependecies here used:
- [Selenium](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
- [TestNG](https://mvnrepository.com/artifact/org.testng/testng)

### *Test Scripts*
**TestNG**: test framework in order to generate test script taking into account the **Page Object Model** design pattern, specific annotations and assertions.

### *POM - Page Object Model*
Page Object Model (POM) is a design pattern, where for each web page in the application, there should be a corresponding Page Class. This Page class will identify the WebElements of that web page and also contains Page methods which perform operations on those WebElements.

### *Page Factory*
Page Factory in Selenium is an inbuilt POM framework concept for Selenium WebDriver but it is very optimized. It is commonly used to initialize Page class elements without using “FindElement(s).”, but using annotations @FindBy.


## Getting Started
To run the tests, go to to Command Prompt (Windows) or Bash (Mac) run (verify the correct project folder):
```
mvn clean test
```
