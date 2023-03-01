package testNG.basics;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGParameters {
	
	@Parameters({"UserNameValue", "passwordValue"})
	@Test
	public void testWithParameters(String userName, String password) {
		System.out.println(userName + "this is userName from parameters");
		System.out.println(password + "this is password from parameters");
		Assert.assertTrue(true);
	}

}
