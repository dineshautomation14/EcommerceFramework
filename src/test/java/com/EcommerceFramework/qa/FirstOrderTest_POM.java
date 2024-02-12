package com.EcommerceFramework.qa;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.EcommerceFramework.Listeners.RetryListener;
import com.EcommerceFramework.qa.TestComponents.BaseTest;
import com.EcommerceFramework.qa.page.ConfirmationPage;
import com.EcommerceFramework.qa.page.MyCartPage;
import com.EcommerceFramework.qa.page.OrderPage;
import com.EcommerceFramework.qa.page.PaymentPage;
import com.EcommerceFramework.qa.page.ProductCatalogue;

public class FirstOrderTest_POM extends BaseTest {

	// class variables
	//String productName = "ZARA COAT 3";
	// class methods
	@Test(groups = {"purchase"}, dataProvider = "getData", priority = 1, retryAnalyzer = RetryListener.class)
	public void submitOrderTest(HashMap<String, String> map) {

		String email = map.get("email");
		String pwd = map.get("pwd");
		String productName = map.get("productName");
		ProductCatalogue productCatologue = landingPage.loginApplication(email, pwd);
		MyCartPage cartPage = productCatologue.addToCart(productName);
		PaymentPage paymentPage = cartPage.checkoutProduct(productName);
		ConfirmationPage confirmationPage = paymentPage.placeOrder();
		Assert.assertEquals(confirmationPage.getConfirmMessage(),"THANKYOU FOR THE ORDER.");

	}
	
	@Test(priority = 2, dataProvider = "getData")
	private void verifyOrderTest(HashMap<String, String> map) {
		String email = map.get("email");
		String pwd = map.get("pwd");
		String productName = map.get("productName");
		ProductCatalogue productCatologue = landingPage.loginApplication(email, pwd);
		OrderPage order = productCatologue.goToOrderPage();
		boolean found = order.verifyOrderPresent(productName);
		Assert.assertTrue(found);
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>>  data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\com\\EcommerceFramework\\data\\purchase.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*
	 * @DataProvider public Object[][] getData() { HashMap<String, String> map0 =
	 * new HashMap<String, String>(); map0.put("email", "ecommuser@gmail.com");
	 * map0.put("pwd", "12qw!@QW"); map0.put("productName","ZARA COAT 3" );
	 * 
	 * HashMap<String, String> map1= new HashMap<String, String>();
	 * map1.put("email", "dinesh123@gmail.com"); map1.put("pwd", "12qw!@QW");
	 * map1.put("productName","IPHONE 13 PRO" );
	 * 
	 * 
	 * return new Object[][] {{map0} , {map1 }}; }
	 */

}
