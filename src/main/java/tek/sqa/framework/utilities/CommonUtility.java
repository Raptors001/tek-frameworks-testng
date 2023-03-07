package tek.sqa.framework.utilities;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import tek.sqa.framework.base.BaseSetup;


import java.time.Duration;

public class CommonUtility extends BaseSetup {

    public WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(20));
    }

    public WebElement waitTillClickable(WebElement element) {
        return this.getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitTillPresence(WebElement element) {
        return this.getWait().until(ExpectedConditions.visibilityOf(element));
    }


    public void click(WebElement element) {
        this.waitTillClickable(element).click();
    }

    public void sendText(WebElement element, String value) {
        this.waitTillPresence(element).sendKeys(value);
    }

    public String getElementText(WebElement element) {
        return this.waitTillPresence(element).getText();
    }

    public String takeScreenShot() {
        String screenShot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        return screenShot;
    }
    
    public void selectValueByVisibleText(WebElement element, String value) {
    	Select select = new Select(element);
    	select.selectByVisibleText(value);
    }
    
}
