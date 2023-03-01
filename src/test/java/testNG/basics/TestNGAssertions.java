package testNG.basics;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGAssertions {
	/**
	 * TestNG Assertions
	 * Hard Assertion
	 * if Assertion failed, test Execution will stop 
	 * Soft Assertion
	 * if Assertion failed, test execution will not stop, 
	 * but marks the test case failed. 
	 */
	@Test
	public void testOne() {
		System.out.println("This is before Hard Assert");
		Assert.assertEquals(10, 10);
		System.out.println("This is after Hard Assert");
	}
	
	@Test (groups = {"SmokeTest"})
	public void testTwo() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("This is before Soft Assert");
		softAssert.assertEquals(10, 10);
		System.out.println("This is after first Soft Assert");
		softAssert.assertTrue(true);
		System.out.println("This is after 2nd Soft Assert");
		softAssert.assertNotEquals(false, true);
		System.out.println("This is after 2nd Soft Assert");
		softAssert.assertAll();
	}
	
}
