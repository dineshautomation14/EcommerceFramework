package com.EcommerceFramework.qa.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.EcommerceFramework.qa.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//My cart page locators
	@FindBy(css="[routerlink*='cart']") WebElement cartLink;
	@FindBy(css="tr td:nth-child(3)") List<WebElement> productNames;
	
	
	
	//My cart page actions
	public boolean verifyOrderPresent(String productName) {
		
		boolean found = productNames.stream().anyMatch(name -> waitForElementVisibility(name).getText().equalsIgnoreCase(productName));
		return found;
	}

}
