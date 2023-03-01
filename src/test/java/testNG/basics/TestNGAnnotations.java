package testNG.basics;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class TestNGAnnotations {
	
	/**
	 * @Test : any block of code under this tag is one Test case or one Scenario 
	 * @BeforeMethod : any block of code under this tag will execute before each test case
	 * @AfterMethod : any block of code under this tag will execute after each test case
	 * @BeforeTest: any block of code under this tag will execute before first Test case 
	 * @AfterTest: any block of code under this tag will execute after last Test case
	 * @BeforeSuite: any block of code under this tag will execute before test suite
	 * @AfterSuite: any block of code under this tag will execute after test suite
	 * @BeforeClass: any block of code under this tag will execute before test case in a class
	 * @AfterClass : any block of code under this tag will execute after test case in a class
	 * @BeforeGroups: any block of code under this tag will execute before a specific group
	 * @AfterGroups: any block of code under this tag will execute after a specific group
	 */

	/**
	 * the most used ones in market 
	 * @Test 
	 * @BeforeMethod
	 * @AfterMethod 
	 * 
	 * @beforeTest and @afterTest
	 * 
	 */
	@Test (priority = 1, groups = {"SmokeTest"})
	public void testOne() {
		Assert.assertTrue(true);
		System.out.println("this is test One");
	}
	@Test(priority = 2, dependsOnMethods = {"testOne"}) 
	public void testTwo() {
		System.out.println("this is test Two");
		System.out.println("this test case will run if test case TestOne passed.");
	}
	@Test (priority = 1)
	public void testThree() {
		System.out.println("this is test Three");
	}
	@Test (priority = 4)
	public void testFour() {
		System.out.println("this is test Four");
	}
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("this method runs before Test");
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("this method runs after Test");
	}
	@BeforeTest 
	public void beforeTest() {
		System.out.println("this method runs before first test");
	}
	@AfterTest
	public void afterTest() {
		System.out.println("this method runs after last test");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
