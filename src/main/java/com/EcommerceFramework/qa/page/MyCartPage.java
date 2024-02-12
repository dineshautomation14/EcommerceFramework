package com.EcommerceFramework.qa.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.EcommerceFramework.qa.AbstractComponents.AbstractComponents;

public class MyCartPage extends AbstractComponents{
	
	WebDriver driver;

	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//My cart page locators
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']") WebElement cartLink;
	@FindBy(css=".cartSection h3") 	List<WebElement> productCards;
	@FindBy(css=".totalRow button")	WebElement checkoutBtn;
	
	
	//My cart page actions
	public PaymentPage checkoutProduct(String productName) {
		
		doClick(cartLink);
		boolean found = productCards.stream().anyMatch(cartProduct -> waitForElementVisibility(cartProduct).getText().equalsIgnoreCase(productName));
		Assert.assertTrue(found);
		doClick(checkoutBtn);
		return new PaymentPage(driver);
	}

}
