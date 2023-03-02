package testNG.basics;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import tek.sqa.framework.base.BaseUITest;

public class JDBCTest extends BaseUITest {

//	public WebDriver driver;

	//@BeforeMethod
	public void beforeMethod() {
		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
//		driver.get("https://tek-retail-ui.azurewebsites.net/");

	}
	@Parameters({"UserNameValue", "passwordValue"})
	@Test
	public void test(String userName, String password) throws InterruptedException {
		getDriver().findElement(By.xpath("//a[text()='Sign in']")).click();
		getDriver().findElement(By.id("email")).sendKeys(userName);
		getDriver().findElement(By.id("password")).sendKeys(password);
		getDriver().findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(5000);
		getDriver().findElement(By.xpath("//a[text()='Orders']")).click();
		String order =getDriver().findElement(By.xpath("(//p[@class='order__header-title'])[4]")).getText();
		String orderNumber = order.substring(8);
		System.out.println(orderNumber);
		List<Map<String, Object>> result =getDbUtility().
				convertResultToMap("Select * from orders where number =" +orderNumber +";");
		String orderNumberDB = result.get(0).get("number").toString();
		System.out.println(orderNumberDB + " this is from DB ");
		Assert.assertEquals(orderNumber, orderNumberDB);
		
		
		
		// we need to write an assertion to check order number from UI is matching to order number 
		// in DB
	}

	//@AfterMethod
	public void afterMethod() {
		//driver.close();
	}

}
