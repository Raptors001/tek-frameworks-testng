package testNG.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGParallelTesting {

	@Test
	public void testOne() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://happy-desert-0f05d560f.1.azurestaticapps.net/");
		Thread.sleep(5000);
		System.out.println(driver.getTitle() + " this is from Insurance");
		System.out.println(Thread.currentThread().getId() + " Thread ID");
		driver.close();
	}

	@Test
	public void testTwo() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://tek-retail-ui.azurewebsites.net/auth/login");
		System.out.println(driver.getTitle() + "this is from retail");
		System.out.println(Thread.currentThread().getId() + " Thread ID");
		driver.close();
	}

}
