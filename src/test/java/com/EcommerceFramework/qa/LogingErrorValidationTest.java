package com.EcommerceFramework.qa;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.EcommerceFramework.qa.TestComponents.BaseTest;

public class LogingErrorValidationTest extends BaseTest{
	
	
	@Test(groups = {"ErrorHandling"})
	public void loginErrorValidationTest() {
		
		landingPage.loginApplication("ecommerce@gmail.com", "1123");
		String error = landingPage.getLoginErrorMessage();
		Assert.assertEquals(error, "Incorrect email or password.");
		
	}
	
	@Test
	public void loginErrorValidationTest01() {
	System.out.println("incomplete test");
		
	}

}
