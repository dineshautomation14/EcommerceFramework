package com.EcommerceFramework.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.EcommerceFramework.qa.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
	private WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	@FindBy(css=".hero-primary") WebElement confirmMesgField;
	
	//page actions
	public String getConfirmMessage() {
		return confirmMesgField.getText();
		
	}
	
	

}
