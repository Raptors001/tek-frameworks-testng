package tek.sqa.framework.base;

import com.aventstack.extentreports.service.ExtentTestManager;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.database.Database;
import org.openqa.selenium.firefox.FirefoxDriver;

import tek.sqa.framework.utilities.DatabaseConnectionUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Properties;

import static tek.sqa.framework.utilities.FileUtilities.getFileInputSteam;

public class BaseSetup {
    private static WebDriver driver;
    private DatabaseConnectionUtility dbUtility;

    public WebDriver getDriver() {
        return driver;
    }

    public DatabaseConnectionUtility getDbUtility() {
        return this.dbUtility;
    }

    private Properties loadProperty() {
        try {
            Properties properties = new Properties();
            String filePath = System.getProperty("user.dir") + "/src/main/resources/config/test_config.properties";
            properties.load(getFileInputSteam(filePath));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Source File not found with message " + e.getMessage());
        }
    }

    public String getProperty(String propertyKey) {
        return loadProperty().getProperty(propertyKey);
    }


    public void setupBrowser() {
        String browserType = this.getProperty("browser");
        String url = this.getProperty("url");
        if (browserType.equalsIgnoreCase("chrome")) {
        	WebDriverManager.chromedriver().setup();
        	driver = new ChromeDriver();
           driver.get(url);
        } else if (browserType.equalsIgnoreCase("firefox")) {
        	WebDriverManager.firefoxdriver().setup();
        	driver = new FirefoxDriver();
            driver.get(url);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
    }


    public void quiteBrowser() {
        if (this.getDriver() != null)
            this.getDriver().close();
        ;
    }

    public void getConnectedToDatabase() {
        this.dbUtility = new DatabaseConnectionUtility(
                getProperty("db_url"),
                getProperty("db_username"),
                getProperty("db_password")
        );
    }


}
