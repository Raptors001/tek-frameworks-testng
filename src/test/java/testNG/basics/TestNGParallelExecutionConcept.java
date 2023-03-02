package testNG.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGParallelExecutionConcept {
	
	
	@Test
	public void testThree() throws InterruptedException {
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get("https://tekschool.us/");
		Thread.sleep(5000);
		System.out.println(driver.getTitle() + " this is from Tek School website");
		System.out.println(Thread.currentThread().getId() + " Thread ID");
		driver.close();
	}

	@Test
	public void testFour() {
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.amazon.com/");
		System.out.println(driver.getTitle() + "this is from amazon");
		System.out.println(Thread.currentThread().getId() + " Thread ID");
		driver.close();
	}

}
