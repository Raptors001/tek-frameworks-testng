# Tekschool TESTNG framework

this repository is base framework design for testng framework.

## Packages and class:

* main.java packages:
    * base: all the classes related to set up framework and test goes under this package
        * BaseSetup: methods for setup UI browsers, reading properties, Database connections, API Connections goes heres
        * BaseUITest: this class will extend to all the UI test related classes. and represent all the methods that
          should run before and after each test or each method.
        * NOTE: BaseUITest is extended to CommonUtility Package in order to have access to all the commons classes.
    * config: All the configuration related classes and interfaces are here in this package.
        * for this framework we decided to have an example for using interface in the framework for students to better
          understanding of using interface subject.
        * this example will help them in interview as well.
        * any other configuration or Setup Classes can be under this package.
    * utilities: all the common methods for UI, Database, API testing can be in this package.
        * CommonUtility: this class is mostly used for UI Testing, and it's extended to BaseSetup in order to have
          access to the static WebDriver.
            * All methods had be developed based on PageFactory POM design.
        * NOTE: If necessary we can add Utilities for API And Database testing as we go forward in class.
* java.resources:
    * for more organizing the directories I've added config directory and all the properties files related to each
      environment can be added to this directory.
    * NOTE: we can make this framework even more dynamic, but I think it's not necessary at this point.
* test.java:
    * config:
        * this config package is represent class called POMFactory(we can come out with better naming if you don't like
          it.)
        * in this framework for better accessibility for element and methods of POM we have decided to come out with
          concept call Factory Builder.
        * as we add more pages this class will get updated. and create on instant of all the pages at the time of object
          instantiation.
        * this class in encapsulated and will be good example for students at interviews.
    * pages:
        * This framework designed base on Page Object Model design (POM) with driver initializer page factory.
        * All the elements related to each page goes under this package.
        * All the pages extends to CommonUtility Class to have access to all the methods.
        * driver initializing should happen at default constructor of the pages.
    * tests:
        * under test packages you can have any package design you want to have. for example here I have smoke and
          regression. (it's all depend on instructor)
        * You can also have UI test, Database test and API tests packages and sub-packages all up to you.
        * Each test extends to the representative BaseTest. e.g: if your test is related to UI it will extend to
          BaseUITest.
        * POMFactory instance should create at ``@BeforeMethod`` level.
        * AfterMethod and closing browser will take care by BaseUITest class.

### Using POMFactory:

in order to use POM Factory for each class gets added to this framework 3 steps should have taken:
step 1: Create private property

```java
  private CSRHomePage csrHomePage;
```

step 2: Instantiate class object in default construction

```java
public POMFactory(){
        this.csrHomePage=new CSRHomePage();
        this.customerServiceLoginPage=new CustomerServiceLoginPage();
        }
```

step 3: create a getter method;

```java
public CSRHomePage homePage(){
        return this.csrHomePage;
        }

```

Usage in test:

```java
    public class SmokeTest extends BaseUITest {
    //POM Factory instantiation: 
    private POMFactory factory;

    @BeforeMethod
    public void initialTest() {
        this.factory = new POMFactory();
    }

    @Test
    public void smokeTest() {
        //here we use POM factory
        factory.csrLogin().login("supervisor", "tek_supervisor");
        String title = getElementText(this.factory.homePage().AppTitle);

        Assert.assertEquals(title, "TEK Insurance Portal");
    }
}
```

### ExtentReport Plugin:

extent report is plugin for testng is the repository we are going to use this framework;

usage:
add maven dependency:

```xml

<dependency>
    <groupId>com.aventstack</groupId>
    <artifactId>extentreports-testng-adapter</artifactId>
    <version>1.2.2</version>
</dependency>
```
add properties under test.resources directory: 
e.g: extent.properties. 
```properties
# spark-reporter
extent.reporter.spark.start=true
extent.reporter.spark.config=src/test/resources/html-config.xml
extent.reporter.spark.out=target/test-output/result/Index.html
```
and html config should add: 
for more detail about extent report testng plugin refer to 
[extent report plugin for testng](https://github.com/extent-framework/extentreports-testng-adapter)
### For more information contact Tek School Technical team. 